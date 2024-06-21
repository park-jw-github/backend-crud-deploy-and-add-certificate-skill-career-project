package com.devdoc.backend.service;

import com.devdoc.backend.dto.*;
import com.devdoc.backend.model.*;
import com.devdoc.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AwardRepository awardRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    // Language 항목 데이터 저장 또는 업데이트
    @Transactional
    public LanguageDTO saveOrUpdateLanguage(int resumeId, LanguageDTO languageDTO) {
        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);
        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            Language language = languageRepository.findByIdAndResumeId(languageDTO.getId(), resumeId)
                    .orElse(new Language());

            boolean isNew = (language.getId() == null); // 새로운 항목인지 확인

            language.setLanguage(languageDTO.getLanguage());
            language.setTestName(languageDTO.getTestName());
            language.setScore(languageDTO.getScore());
            language.setDate(languageDTO.getDate());
            language.setResume(resume);

            Language savedLanguage = languageRepository.save(language);

            return new LanguageDTO(savedLanguage.getId(), savedLanguage.getLanguage(), savedLanguage.getTestName(), savedLanguage.getScore(), savedLanguage.getDate());
        }
        throw new RuntimeException("Resume not found");
    }

    // Language 항목 데이터 삭제
    @Transactional
    public void deleteLanguage(int resumeId, int languageId) {
        Optional<Language> language = languageRepository.findByIdAndResumeId(languageId, resumeId);
        language.ifPresent(languageRepository::delete);
    }

    // Award 항목 데이터 저장 또는 업데이트
    @Transactional
    public AwardDTO saveOrUpdateAward(int resumeId, AwardDTO awardDTO) {
        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);
        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            Award award = awardRepository.findByIdAndResumeId(awardDTO.getId(), resumeId)
                    .orElse(new Award());

            boolean isNew = (award.getId() == null);

            award.setAwardName(awardDTO.getAwardName());
            award.setAwardingInstitution(awardDTO.getAwardingInstitution());
            award.setDate(awardDTO.getDate());
            award.setDescription(awardDTO.getDescription());
            award.setResume(resume);

            Award savedAward = awardRepository.save(award);

            return new AwardDTO(savedAward.getId(), savedAward.getAwardName(), savedAward.getAwardingInstitution(), savedAward.getDate(), savedAward.getDescription());
        }
        throw new RuntimeException("Resume not found");
    }

    // Award 항목 데이터 삭제
    @Transactional
    public void deleteAward(int resumeId, int awardId) {
        Optional<Award> award = awardRepository.findByIdAndResumeId(awardId, resumeId);
        award.ifPresent(awardRepository::delete);
    }

    // Certificate 항목 데이터 저장 또는 업데이트
    @Transactional
    public CertificateDTO saveOrUpdateCertificate(int resumeId, CertificateDTO certificateDTO) {
        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);
        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            // 자격증 목록 중 하나의 항목 불러오기 (없으면 새로 생성)
            Certificate certificate = certificateRepository.findByIdAndResumeId(certificateDTO.getId(), resumeId)
                    .orElse(new Certificate());

            boolean isNew = (certificate.getId() == null);

            // 항목에 대한 정보 입력
            certificate.setCertificateName(certificateDTO.getCertificateName());
            certificate.setIssuer(certificateDTO.getIssuer());
            certificate.setIssueDate(certificateDTO.getIssueDate());
            certificate.setResume(resume);

            // 입력한 내용을 저장
            Certificate savedCertificate = certificateRepository.save(certificate);

            return new CertificateDTO(savedCertificate.getId(), savedCertificate.getCertificateName(), savedCertificate.getIssuer(), savedCertificate.getIssueDate());
        }
        throw new RuntimeException("Resume not found");
    }

    // Certificate 항목 데이터 삭제
    @Transactional
    public void deleteCertificate(int resumeId, int certificateId) {
        Optional<Certificate> certificate = certificateRepository.findByIdAndResumeId(certificateId, resumeId);
        certificate.ifPresent(certificateRepository::delete);
    }

    // Skill 항목 데이터 저장 또는 업데이트
    @Transactional
    public SkillDTO saveOrUpdateSkill(int resumeId, SkillDTO skillDTO) {
        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);
        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            Skill skill = skillRepository.findByIdAndResumeId(skillDTO.getId(), resumeId)
                    .orElse(new Skill());

            boolean isNew = (skill.getId() == null); // 새로운 항목인지 확인

            skill.setTechStack(skillDTO.getTechStack());
            skill.setDescription(skillDTO.getDescription());
            skill.setResume(resume);

            Skill savedSkill = skillRepository.save(skill);

            return new SkillDTO(savedSkill.getId(), savedSkill.getTechStack(), savedSkill.getDescription());
        }
        throw new RuntimeException("Resume not found");
    }

    // Skill 항목 데이터 삭제
    @Transactional
    public void deleteSkill(int resumeId, int skillId) {
        Optional<Skill> skill = skillRepository.findByIdAndResumeId(skillId, resumeId);
        skill.ifPresent(skillRepository::delete);
    }

    // Career 항목 데이터 저장 또는 업데이트
    @Transactional
    public CareerDTO saveOrUpdateCareer(int resumeId, CareerDTO careerDTO) {
        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);
        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            Career career = careerRepository.findByIdAndResumeId(careerDTO.getId(), resumeId)
                    .orElse(new Career());

            boolean isNew = (career.getId() == null); // 새로운 항목인지 확인

            career.setCompany(careerDTO.getCompany());
            career.setDepartment(careerDTO.getDepartment());
            career.setStartDate(careerDTO.getStartDate());
            career.setEndDate(careerDTO.getEndDate());
            career.setIsCurrent(careerDTO.getIsCurrent());
            career.setTechStack(careerDTO.getTechStack());
            career.setDescription(careerDTO.getDescription());
            career.setResume(resume);

            Career savedCareer = careerRepository.save(career);

            return new CareerDTO(savedCareer.getId(), savedCareer.getCompany(), savedCareer.getDepartment(), savedCareer.getStartDate(), savedCareer.getEndDate(), savedCareer.getIsCurrent(), savedCareer.getTechStack(), savedCareer.getDescription());
        }
        throw new RuntimeException("Resume not found");
    }

    // Career 항목 데이터 삭제
    @Transactional
    public void deleteCareer(int resumeId, int careerId) {
        Optional<Career> career = careerRepository.findByIdAndResumeId(careerId, resumeId);
        career.ifPresent(careerRepository::delete);
    }

    // Project 항목 데이터 저장 또는 업데이트
    @Transactional
    public ProjectDTO saveOrUpdateProject(int resumeId, ProjectDTO projectDTO) {
        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);
        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            Project project = projectRepository.findByIdAndResumeId(projectDTO.getId(), resumeId)
                    .orElse(new Project());

            boolean isNew = (project.getId() == null); // 새로운 항목인지 확인

            project.setTitle(projectDTO.getTitle());
            project.setStartDate(projectDTO.getStartDate());
            project.setEndDate(projectDTO.getEndDate());
            project.setIsCurrent(projectDTO.getIsCurrent());
            project.setIntro(projectDTO.getIntro());
            project.setTechStack(projectDTO.getTechStack());
            project.setDescription(projectDTO.getDescription());
            project.setResume(resume);

            Project savedProject = projectRepository.save(project);

            return new ProjectDTO(savedProject.getId(), savedProject.getTitle(), savedProject.getStartDate(), savedProject.getEndDate(), savedProject.getIsCurrent(), savedProject.getIntro(), savedProject.getTechStack(), savedProject.getDescription());
        }
        throw new RuntimeException("Resume not found");
    }

    // Project 항목 데이터 삭제
    @Transactional
    public void deleteProject(int resumeId, int projectId) {
        Optional<Project> project = projectRepository.findByIdAndResumeId(projectId, resumeId);
        project.ifPresent(projectRepository::delete);
    }

    // 이력서 저장
    @Transactional
    public void saveResume(int resumeId, ResumeDTO resumeDTO) {
        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);
        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            resume.setTitle(resumeDTO.getTitle());

            List<Language> languages = resumeDTO.getLanguages().stream()
                    .map(languageDTO -> new Language(languageDTO.getId(), languageDTO.getLanguage(), languageDTO.getTestName(), languageDTO.getScore(), languageDTO.getDate(), resume))
                    .collect(Collectors.toList());
            resume.setLanguages(languages);

            List<Award> awards = resumeDTO.getAwards().stream()
                    .map(awardDTO -> new Award(awardDTO.getId(), awardDTO.getAwardName(), awardDTO.getAwardingInstitution(), awardDTO.getDate(), awardDTO.getDescription(), resume))
                    .collect(Collectors.toList());
            resume.setAwards(awards);

            List<Certificate> certificates = resumeDTO.getCertificates().stream()
                    .map(certificateDTO -> new Certificate(certificateDTO.getId(), certificateDTO.getCertificateName(), certificateDTO.getIssuer(), certificateDTO.getIssueDate(), resume))
                    .collect(Collectors.toList());
            resume.setCertificates(certificates);

            List<Skill> skills = resumeDTO.getSkills().stream()
                    .map(skillDTO -> new Skill(skillDTO.getId(), skillDTO.getTechStack(), skillDTO.getDescription(), resume))
                    .collect(Collectors.toList());
            resume.setSkills(skills);

            List<Career> careers = resumeDTO.getCareers().stream()
                    .map(careerDTO -> new Career(careerDTO.getId(), careerDTO.getCompany(), careerDTO.getDepartment(), careerDTO.getStartDate(), careerDTO.getEndDate(), careerDTO.getIsCurrent(), careerDTO.getTechStack(), careerDTO.getDescription(), resume))
                    .collect(Collectors.toList());
            resume.setCareers(careers);

            List<Project> projects = resumeDTO.getProjects().stream()
                    .map(projectDTO -> new Project(projectDTO.getId(), projectDTO.getTitle(), projectDTO.getStartDate(), projectDTO.getEndDate(), projectDTO.getIsCurrent(), projectDTO.getIntro(), projectDTO.getTechStack(), projectDTO.getDescription(), resume))
                    .collect(Collectors.toList());
            resume.setProjects(projects);

            resumeRepository.save(resume);
        }
    }

    // 특정 이력서 조회
    public ResumeDTO getResumeByResumeId(int resumeId) {
        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);
        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();

            List<LanguageDTO> languageDTOs = resume.getLanguages().stream()
                    .map(language -> new LanguageDTO(language.getId(), language.getLanguage(), language.getTestName(), language.getScore(), language.getDate()))
                    .collect(Collectors.toList());

            List<AwardDTO> awardDTOs = resume.getAwards().stream()
                    .map(award -> new AwardDTO(award.getId(), award.getAwardName(), award.getAwardingInstitution(), award.getDate(), award.getDescription()))
                    .collect(Collectors.toList());

            List<CertificateDTO> certificateDTOs = resume.getCertificates().stream()
                    .map(certificate -> new CertificateDTO(certificate.getId(), certificate.getCertificateName(), certificate.getIssuer(), certificate.getIssueDate()))
                    .collect(Collectors.toList());

            List<SkillDTO> skillDTOs = resume.getSkills().stream()
                    .map(skill -> new SkillDTO(skill.getId(), skill.getTechStack(), skill.getDescription()))
                    .collect(Collectors.toList());

            List<CareerDTO> careerDTOs = resume.getCareers().stream()
                    .map(career -> new CareerDTO(career.getId(), career.getCompany(), career.getDepartment(), career.getStartDate(), career.getEndDate(), career.getIsCurrent(), career.getTechStack(), career.getDescription()))
                    .collect(Collectors.toList());

            List<ProjectDTO> projectDTOs = resume.getProjects().stream()
                    .map(project -> new ProjectDTO(project.getId(), project.getTitle(), project.getStartDate(), project.getEndDate(), project.getIsCurrent(), project.getIntro(), project.getTechStack(), project.getDescription()))
                    .collect(Collectors.toList());

            return new ResumeDTO(resume.getId(), resume.getTitle(), resume.getCreatedAt(), languageDTOs, awardDTOs, certificateDTOs, skillDTOs, careerDTOs, projectDTOs);
        }
        return null;
    }

    // 모든 이력서 조회
    public List<ResumeDTO> getAllResumes() {
        List<Resume> resumes = resumeRepository.findAll();
        return resumes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // 이력서를 DTO로 변환
    private ResumeDTO convertToDTO(Resume resume) {
        List<LanguageDTO> languages = languageRepository.findByResumeId(resume.getId())
                .stream()
                .map(language -> new LanguageDTO(language.getId(), language.getLanguage(), language.getTestName(), language.getScore(), language.getDate()))
                .collect(Collectors.toList());

        List<AwardDTO> awards = awardRepository.findByResumeId(resume.getId())
                .stream()
                .map(award -> new AwardDTO(award.getId(), award.getAwardName(), award.getAwardingInstitution(), award.getDate(), award.getDescription()))
                .collect(Collectors.toList());

        List<CertificateDTO> certificates = certificateRepository.findByResumeId(resume.getId())
                .stream()
                .map(certificate -> new CertificateDTO(certificate.getId(), certificate.getCertificateName(), certificate.getIssuer(), certificate.getIssueDate()))
                .collect(Collectors.toList());

        List<SkillDTO> skills = skillRepository.findByResumeId(resume.getId())
                .stream()
                .map(skill -> new SkillDTO(skill.getId(), skill.getTechStack(), skill.getDescription()))
                .collect(Collectors.toList());

        List<CareerDTO> careers = careerRepository.findByResumeId(resume.getId())
                .stream()
                .map(career -> new CareerDTO(career.getId(), career.getCompany(), career.getDepartment(), career.getStartDate(), career.getEndDate(), career.getIsCurrent(), career.getTechStack(), career.getDescription()))
                .collect(Collectors.toList());

        List<ProjectDTO> projects = projectRepository.findByResumeId(resume.getId())
                .stream()
                .map(project -> new ProjectDTO(project.getId(), project.getTitle(), project.getStartDate(), project.getEndDate(), project.getIsCurrent(), project.getIntro(), project.getTechStack(), project.getDescription()))
                .collect(Collectors.toList());

        return new ResumeDTO(resume.getId(), resume.getTitle(), resume.getCreatedAt(), languages, awards, certificates, skills, careers, projects);
    }

    // 특정 사용자의 모든 이력서 조회
    public List<ResumeDTO> getAllResumesByUser(String userId) {
        List<Resume> resumes = resumeRepository.findByUserId(userId);
        return resumes.stream().map(resume -> new ResumeDTO(resume.getId(), resume.getTitle(), resume.getCreatedAt(), null, null, null, null, null, null)).collect(Collectors.toList());
    }

    // 새로운 이력서 생성
    @Transactional
    public Resume createResume(String title, String userId) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            Resume resume = new Resume();
            resume.setTitle(title);
            resume.setUser(user);
            resume = resumeRepository.save(resume);
            return resume;
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // 이력서 삭제
    @Transactional
    public void deleteResumeByResumeId(int resumeId) {
        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);
        optionalResume.ifPresent(resumeRepository::delete);
    }

    // 이력서 제목 저장
    @Transactional
    public ResumeDTO saveResumeTitleByResumeId(int resumeId, String newTitle) {
        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);
        if (optionalResume.isPresent()) {
            Resume resume = optionalResume.get();
            resume.setTitle(newTitle);
            resumeRepository.save(resume);
            return new ResumeDTO(resume.getId(), resume.getTitle(), resume.getCreatedAt(), null, null, null, null, null, null);
        }
        return null;
    }
}

