package ch.wiss.unternehmensliste.service;

import ch.wiss.unternehmensliste.model.*;
import ch.wiss.unternehmensliste.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * Die DataBaseLoader Klasse wird verwendet, um die Datenbank mit Testdaten zu füllen.
 */

@Component
public class DataBaseLoader implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final JobApplicationRepository jobApplicationRepository;
    private final CompanyRepository companyRepository;
    private final ContactRepository contactRepository;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public DataBaseLoader(PasswordEncoder passwordEncoder, JobApplicationRepository jobApplicationRepository, CompanyRepository companyRepository, ContactRepository contactRepository, RoleRepository roleRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.companyRepository = companyRepository;
        this.contactRepository = contactRepository;
        this.jobApplicationRepository = jobApplicationRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }
    //Hier werden die Testdaten erstellt und in die Datenbank gespeichert.
    @Override
    public void run(String... strings) throws Exception {
        if(roleRepository.count() > 0) {
            return;
        }
        this.roleRepository.save(new Role(Role.ERole.ROLE_USER));
        this.roleRepository.save(new Role(Role.ERole.ROLE_ADMIN));
        this.roleRepository.save(new Role(Role.ERole.ROLE_COMPANY));
        User user = new User("janedoe", passwordEncoder.encode("p@ssw0rd"), "jane.doe@email.com");
        user.setRoles(new HashSet<>(Arrays.asList(
                roleRepository.findByName(Role.ERole.ROLE_USER).get(),
                roleRepository.findByName(Role.ERole.ROLE_COMPANY).get())));
        this.userRepository.save(user);

        User user2 = new User("alicegreen", passwordEncoder.encode("mypassword"), "alice.green@email.com");
        user2.setRoles(new HashSet<>(Collections.singletonList(
                roleRepository.findByName(Role.ERole.ROLE_USER).get())));
        this.userRepository.save(user2);

        User user3 = new User("tomjones", passwordEncoder.encode("t0mpass"), "tom.jones@email.com");
        user3.setRoles(new HashSet<>(Arrays.asList(
                roleRepository.findByName(Role.ERole.ROLE_USER).get(),
                roleRepository.findByName(Role.ERole.ROLE_ADMIN).get())));
        this.userRepository.save(user3);

        User user4 = new User("marysmith", passwordEncoder.encode("maryspassword"), "mary.smith@email.com");
        user4.setRoles(new HashSet<>(Arrays.asList(
                roleRepository.findByName(Role.ERole.ROLE_USER).get(),
                roleRepository.findByName(Role.ERole.ROLE_COMPANY).get())));
        this.userRepository.save(user4);

        // Microsoft
        this.companyRepository.save(new Company("Microsoft", "www.microsoft.com", "Wallisellen"));
        this.contactRepository.save(new Contact("female", "Microsoft Contact", "0715398321", "microsoft@gmail.com"));
        this.jobApplicationRepository.save(new JobApplication("Software-Entwickler", "microsoftstrasse", 8304, "nicht kontaktiert", contactRepository.findById(1), companyRepository.findById(1), LocalDateTime.now()));

        // Google
        this.companyRepository.save(new Company("Google", "www.google.com", "Zürich"));
        this.contactRepository.save(new Contact("male", "Google Contact", "0715398322", "google@gmail.com"));
        this.jobApplicationRepository.save(new JobApplication("Applikationsentwickler", "googlestrasse", 8000, "nicht Kontaktiert", contactRepository.findById(2), companyRepository.findById(2), LocalDateTime.now()));

        // Ergon Informatik
        this.companyRepository.save(new Company("Ergon Informatik", "www.ergoninformatik.com", "Zürich"));
        this.contactRepository.save(new Contact("male", "Ergon Informatik Contact", "0715393323", "ergon@ergon.com"));
        this.jobApplicationRepository.save(new JobApplication("Applikationsentwickler", "ergonstrasse", 8000, "nicht Kontaktiert", contactRepository.findById(3), companyRepository.findById(3), LocalDateTime.now()));

        // ABB
        this.companyRepository.save(new Company("ABB", "www.abb.com", "Zürich"));
        this.contactRepository.save(new Contact("male", "ABB Contact", "0715398324", "abb@gmail.com"));
        this.jobApplicationRepository.save(new JobApplication("Software-Entwickler", "abbstrasse", 8000, "nicht kontaktiert", contactRepository.findById(4), companyRepository.findById(4), LocalDateTime.now()));

        // Swisscom
        this.companyRepository.save(new Company("Swisscom", "www.swisscom.ch", "Bern"));
        this.contactRepository.save(new Contact("female", "Swisscom Contact", "0715398325", "swisscom@gmail.com"));
        this.jobApplicationRepository.save(new JobApplication("IT-Spezialist", "swisscomstrasse", 3000, "nicht kontaktiert", contactRepository.findById(5), companyRepository.findById(5), LocalDateTime.now()));

        // Roche
        this.companyRepository.save(new Company("Roche", "www.roche.com", "Basel"));
        this.contactRepository.save(new Contact("male", "Roche Contact", "0715398326", "roche@gmail.com"));
        this.jobApplicationRepository.save(new JobApplication("Data Scientist", "rochestraße", 4000, "nicht kontaktiert", contactRepository.findById(6), companyRepository.findById(6), LocalDateTime.now()));

        // Credit Suisse
        this.companyRepository.save(new Company("Credit Suisse", "www.credit-suisse.com", "Zürich"));
        this.contactRepository.save(new Contact("female", "Credit Suisse Contact", "0715398327", "creditsuisse@gmail.com"));
        this.jobApplicationRepository.save(new JobApplication("Finanzanalyst", "credit-suisse-strasse", 8000, "nicht kontaktiert", contactRepository.findById(7), companyRepository.findById(7), LocalDateTime.now()));

        // Novartis
        this.companyRepository.save(new Company("Novartis", "www.novartis.com", "Basel"));
        this.contactRepository.save(new Contact("male", "Novartis Contact", "0715398328", "novartis@gmail.com"));
        this.jobApplicationRepository.save(new JobApplication("Pharmazeutischer Techniker", "novartisstrasse", 4000, "nicht kontaktiert", contactRepository.findById(8), companyRepository.findById(8), LocalDateTime.now()));

        // Nestlé
        this.companyRepository.save(new Company("Nestlé", "www.nestle.com", "Vevey"));
        this.contactRepository.save(new Contact("female", "Nestlé Contact", "0715398329", "nestle@gmail.com"));
        this.jobApplicationRepository.save(new JobApplication("Marketing-Manager", "nestlé-strasse", 1800, "nicht kontaktiert", contactRepository.findById(9), companyRepository.findById(9), LocalDateTime.now()));

        // ABBOTT
        this.companyRepository.save(new Company("ABBOTT", "www.abbott.com", "Baar"));
        this.contactRepository.save(new Contact("male", "ABBOTT Contact", "0715398330", "abbott@gmail.com"));
        this.jobApplicationRepository.save(new JobApplication("IT-Support-Techniker", "abbottstrasse", 6341, "nicht kontaktiert", contactRepository.findById(10), companyRepository.findById(10), LocalDateTime.now()));
    }
}
