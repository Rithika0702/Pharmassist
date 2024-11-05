package com.jsp.pharmassist.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.jsp.pharmassist.entity.Patient;
import com.jsp.pharmassist.exception.NoPatientsFoundException;
import com.jsp.pharmassist.exception.PatientNotFoundByIdException;
import com.jsp.pharmassist.exception.PharmacyNotFoundByIdException;
import com.jsp.pharmassist.mapper.PatientMapper;
import com.jsp.pharmassist.repository.PatientRepository;
import com.jsp.pharmassist.repository.PharmacyRepository;
import com.jsp.pharmassist.requestdtos.PatientRequest;
import com.jsp.pharmassist.responsedtos.PatientResponse;

@Service
public class PatientService {
	
	private final PatientRepository patientRepository;
	private final PharmacyRepository pharmacyRepository;
	private final PatientMapper patientMapper;

	public PatientService(PatientRepository patientRepository, PharmacyRepository pharmacyRepository,
			PatientMapper patientMapper) {
		super();
		this.patientRepository = patientRepository;
		this.pharmacyRepository = pharmacyRepository;
		this.patientMapper = patientMapper;
	}

	public PatientResponse addPatient( PatientRequest patientRequest, String pharmacyId) {

		return pharmacyRepository.findById(pharmacyId)
				.map(pharmacy  ->{
					Patient patient=patientMapper.mapToPatient(patientRequest, new Patient());
					patient.setPharmacy(pharmacy);
					pharmacy.getPatients().add(patient);
					patientRepository.save(patient);
					return patientMapper.mapToPatientResponse(patient);
				})
				.orElseThrow(() ->new PharmacyNotFoundByIdException("Failed to find Pharmacy"));
                        
	}

	public PatientResponse updatePatient(PatientRequest patientRequest, String patientId) {
		
	return 	patientRepository.findById(patientId)
		                    .map(exPatient -> {
		                      	patientMapper.mapToPatient(patientRequest, exPatient);
			                   return patientRepository.save(exPatient);
		                    })
		                    .map(patientMapper::mapToPatientResponse)
		              .orElseThrow(() -> new PatientNotFoundByIdException("Failed to update Patient"));
		
		
	}

	public List<PatientResponse> findAllPatientByPharmacyId(String pharmacyId) {
			
		 return pharmacyRepository.findById(pharmacyId)
			        .map(pharmacy -> patientRepository.findByPharmacy(pharmacy))
			        .filter(patients -> !patients.isEmpty())
			        .orElseThrow(() -> new NoPatientsFoundException("Failed to find patients associated with the pharmacyID: " + pharmacyId))
			        .stream()
			        .map(patientMapper::mapToPatientResponse)
			        .toList();
			        
       
		}
	

}
