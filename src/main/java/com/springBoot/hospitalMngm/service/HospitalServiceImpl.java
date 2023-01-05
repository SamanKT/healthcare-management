package com.springBoot.hospitalMngm.service;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springBoot.hospitalMngm.dto.AppUserDto;
import com.springBoot.hospitalMngm.formatter.MyDateFormatter;
import com.springBoot.hospitalMngm.model.AppRole;
import com.springBoot.hospitalMngm.model.AppUser;
import com.springBoot.hospitalMngm.model.Patient;
import com.springBoot.hospitalMngm.repository.HospitalRepository;
import com.springBoot.hospitalMngm.repository.PatientRepository;
import com.springBoot.hospitalMngm.repository.RoleRepository;

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalRepository repository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public AppUserDto save(AppUserDto userDto) {
		AppUser appUser = mapper.map(userDto, AppUser.class);
		appUser.setPassword(encoder.encode(userDto.getPassword()));
		Set<AppRole> appRoles = appUser.getAppRoles();

		if (roleRepository.findByName("USER").isEmpty())
			appRoles.add(new AppRole("USER"));
		else
			appRoles.add(roleRepository.findByName("USER").get());

		List<String> pres1 = new ArrayList<String>(Arrays.asList("Celexa20mg","Atenolol10mg"));
		List<String> pres2 = new ArrayList<String>(Arrays.asList("Crestor20mg","Atenolol10mg"));
		List<String> pres3 = new ArrayList<String>(Arrays.asList("Diane20mg","Resov10mg"));
		List<String> pres4 = new ArrayList<String>(Arrays.asList("Parol10mg","Atenolol10mg"));
		List<String> pres5 = 	new ArrayList<String>(Arrays.asList("Flex20mg","Atenolol10mg"));
		List<Patient> patients = new ArrayList<Patient>(
				Arrays.asList(
						new Patient("Vilma Stone",
								LocalDateTime.now().plusHours(1)
										.format(DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:00")),
										pres1,
								appUser),
						new Patient ("Karl Shane",
								LocalDateTime.now().plusHours(2)
										.format(DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:00"))
										,pres2,
								appUser),
						new Patient("Messsy Adam",
								LocalDateTime.now().plusHours(3)
										.format(DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:00")),
										pres3,
								appUser),
						new Patient("John Richards",
								LocalDateTime.now().plusDays(1)
										.format(DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:00")),
										pres4,
								appUser),
						new Patient("Henry Tom", LocalDateTime.now().plusDays(1)
								.format(DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:00")),
								pres5,
								appUser)));

		
		appUser.setPatients(patients);

		return mapper.map(repository.save(appUser), AppUserDto.class);
	}

	@Override
	public List<AppUserDto> getAll() {
		Type type = new TypeToken<List<AppUserDto>>() {
		}.getType();

		return mapper.map(repository.findAll(), type);
	}

	@Override
	public AppUserDto getByUserName(String userName) {

		return mapper.map(repository.findByUserName(userName), AppUserDto.class);
	}

	@Override
	public AppUserDto getByName(String name) {

		return mapper.map(repository.findByName(name), AppUserDto.class);
	}

	@Override
	public void addNewPatient(Patient patient, String name) throws Exception {
		AppUser appUser = repository.findByName(name).get();
		MyDateFormatter formater = new MyDateFormatter();

		String correctedDateTime = formater.parse(patient.getAppointment(), Locale.getDefault());
		patient.setAppointment(correctedDateTime);
		patient.setDoctor(appUser);

		if (appUser.getPatients().stream().map(a -> a.getName()).collect(Collectors.toList())
				.contains(patient.getName())) {
			
			Patient existedPatient = appUser.getPatients().stream().filter(a -> a.getName().equals(patient.getName()))
					.toList().get(0);
			existedPatient.setAppointment(patient.getAppointment());
			existedPatient.setName(patient.getName());
			existedPatient.setNote(patient.getNote());
		} else
			appUser.getPatients().add(patient);

		repository.save(appUser);

	}

	@Override
	public List<Patient> getPatientsOfDoc(String name) {

		return repository.findByName(name).get().getPatients().stream().toList();
	}

	@Override
	public Patient patientById(int patientId) {
		
		return patientRepository.findById(patientId).get();
	}

	@Override
	public void deletePatient(int id) {
		
		patientRepository.delete(patientById(id));
	}

	@Override
	public void addPrescriptionToPatient(int id, String prescription, String docName) {
		
		AppUser doctor = repository.findByName(docName).get();
		Patient patient = doctor.getPatients().stream().filter(a -> a.getId()==id).toList().get(0);
		patient.getPresciptions().add(prescription);
		repository.save(doctor);
		
	}

}
