// ResumeController.java
package com.devdoc.backend.controller;

import com.devdoc.backend.dto.*;
import com.devdoc.backend.model.Resume;
import com.devdoc.backend.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    /* Language 관련 메소드 시작 */

    // language 데이터 저장 또는 수정
    @PostMapping("/{resumeId}/languages")
    public ResponseEntity<LanguageDTO> saveOrUpdateLanguage(@PathVariable int resumeId, @RequestBody LanguageDTO languageDTO) {
        try {
            LanguageDTO updatedLanguage = resumeService.saveOrUpdateLanguage(resumeId, languageDTO);
            return ResponseEntity.ok(updatedLanguage); // 업데이트된 language 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 서버 에러 발생 시 500 반환
        }
    }

    // language 데이터 삭제
    @DeleteMapping("/{resumeId}/languages/{languageId}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable int resumeId, @PathVariable int languageId) {
        try {
            resumeService.deleteLanguage(resumeId, languageId);
            return ResponseEntity.noContent().build(); // language 데이터 삭제 후 no content 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // language 데이터 수정
    @PutMapping("/{resumeId}/languages")
    public ResponseEntity<LanguageDTO> updateLanguage(@PathVariable int resumeId, @RequestBody LanguageDTO languageDTO) {
        try {
            LanguageDTO updatedLanguage = resumeService.saveOrUpdateLanguage(resumeId, languageDTO);
            return ResponseEntity.ok(updatedLanguage); // 수정된 language 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Award 데이터 저장 또는 수정
    @PostMapping("/{resumeId}/awards")
    public ResponseEntity<AwardDTO> saveOrUpdateAward(@PathVariable int resumeId, @RequestBody AwardDTO awardDTO) {
        try {
            AwardDTO updatedAward = resumeService.saveOrUpdateAward(resumeId, awardDTO);
            return ResponseEntity.ok(updatedAward); // 수정된 Award 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /* Language 관련 메소드 끝 */
    /* Award 관련 메소드 시작 */

    // Award 데이터 삭제
    @DeleteMapping("/{resumeId}/awards/{awardId}")
    public ResponseEntity<Void> deleteAward(@PathVariable int resumeId, @PathVariable int awardId) {
        try {
            resumeService.deleteAward(resumeId, awardId);
            return ResponseEntity.noContent().build(); //Award 데이터 삭제 후 no content 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Award 데이터 수정
    @PutMapping("/{resumeId}/awards")
    public ResponseEntity<AwardDTO> updateAward(@PathVariable int resumeId, @RequestBody AwardDTO awardDTO) {
        try {
            AwardDTO updatedAward = resumeService.saveOrUpdateAward(resumeId, awardDTO);
            return ResponseEntity.ok(updatedAward); // 업데이트된 Award 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /* Award 관련 메소드 끝 */

    // Certificate 데이터 저장
    @PostMapping("/{resumeId}/certificates")
    public ResponseEntity<CertificateDTO> saveOrUpdateCertificate(@PathVariable int resumeId, @RequestBody CertificateDTO certificateDTO) {
        try {
            // 입력 받은 내용을 CertificateDTO를 통해 전달
            CertificateDTO updatedCertificate = resumeService.saveOrUpdateCertificate(resumeId, certificateDTO);
            return ResponseEntity.ok(updatedCertificate); // 업데이트한 Certificate 내용을 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Certificate 데이터 삭제
    @DeleteMapping("/{resumeId}/certificates/{certificateId}")
    public ResponseEntity<Void> deleteCertificate(@PathVariable int resumeId, @PathVariable int certificateId) {
        try {
            resumeService.deleteCertificate(resumeId, certificateId); // 해당 Certificate 항목을 저장소에서 삭제
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Certificate 데이터 수정
    @PutMapping("/{resumeId}/certificates")
    public ResponseEntity<CertificateDTO> updateCertificate(@PathVariable int resumeId, @RequestBody CertificateDTO certificateDTO) {
        try {
            CertificateDTO updatedCertificate = resumeService.saveOrUpdateCertificate(resumeId, certificateDTO);
            return ResponseEntity.ok(updatedCertificate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /* Skill 관련 메소드 시작 */

    // Skill 데이터 저장 또는 수정
    @PostMapping("/{resumeId}/skills")
    public ResponseEntity<SkillDTO> saveOrUpdateSkill(@PathVariable int resumeId, @RequestBody SkillDTO skillDTO) {
        try {
            SkillDTO updatedSkill = resumeService.saveOrUpdateSkill(resumeId, skillDTO);
            return ResponseEntity.ok(updatedSkill); // 수정된 Skill 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Skill 데이터 삭제
    @DeleteMapping("/{resumeId}/skills/{skillId}")
    public ResponseEntity<Void> deleteSkill(@PathVariable int resumeId, @PathVariable int skillId) {
        try {
            resumeService.deleteSkill(resumeId, skillId);
            return ResponseEntity.noContent().build(); //Skill 데이터 삭제 후 no content 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Skill 데이터 수정
    @PutMapping("/{resumeId}/skills")
    public ResponseEntity<SkillDTO> updateSkill(@PathVariable int resumeId, @RequestBody SkillDTO skillDTO) {
        try {
            SkillDTO updatedSkill = resumeService.saveOrUpdateSkill(resumeId, skillDTO);
            return ResponseEntity.ok(updatedSkill); // 업데이트된 Skill 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /* Skill 관련 메소드 끝 */
    /* Career 관련 메소드 시작 */

    // Career 데이터 저장 또는 수정
    @PostMapping("/{resumeId}/careers")
    public ResponseEntity<CareerDTO> saveOrUpdateCareer(@PathVariable int resumeId, @RequestBody CareerDTO careerDTO) {
        try {
            CareerDTO updatedCareer = resumeService.saveOrUpdateCareer(resumeId, careerDTO);
            return ResponseEntity.ok(updatedCareer); // 수정된 Career 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Career 데이터 삭제
    @DeleteMapping("/{resumeId}/careers/{careerId}")
    public ResponseEntity<Void> deleteCareer(@PathVariable int resumeId, @PathVariable int careerId) {
        try {
            resumeService.deleteCareer(resumeId, careerId);
            return ResponseEntity.noContent().build(); //Career 데이터 삭제 후 no content 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Career 데이터 수정
    @PutMapping("/{resumeId}/careers")
    public ResponseEntity<CareerDTO> updateCareer(@PathVariable int resumeId, @RequestBody CareerDTO careerDTO) {
        try {
            CareerDTO updatedCareer = resumeService.saveOrUpdateCareer(resumeId, careerDTO);
            return ResponseEntity.ok(updatedCareer); // 업데이트된 Career 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /* Career 관련 메소드 끝 */
    /* Project 관련 메소드 시작 */

    // Project 데이터 저장 또는 수정
    @PostMapping("/{resumeId}/projects")
    public ResponseEntity<ProjectDTO> saveOrUpdateProject(@PathVariable int resumeId, @RequestBody ProjectDTO projectDTO) {
        try {
            ProjectDTO updatedProject = resumeService.saveOrUpdateProject(resumeId, projectDTO);
            return ResponseEntity.ok(updatedProject); // 수정된 Project 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Project 데이터 삭제
    @DeleteMapping("/{resumeId}/projects/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable int resumeId, @PathVariable int projectId) {
        try {
            resumeService.deleteProject(resumeId, projectId);
            return ResponseEntity.noContent().build(); //Project 데이터 삭제 후 no content 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Project 데이터 수정
    @PutMapping("/{resumeId}/projects")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable int resumeId, @RequestBody ProjectDTO projectDTO) {
        try {
            ProjectDTO updatedProject = resumeService.saveOrUpdateProject(resumeId, projectDTO);
            return ResponseEntity.ok(updatedProject); // 업데이트된 Project 데이터 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /* Project 관련 메소드 끝 */

    // 이력서 저장
    @PostMapping("/{resumeId}/save")
    public ResponseEntity<?> saveResume(@PathVariable int resumeId, @RequestBody ResumeDTO resumeDTO) {
        try {
            resumeService.saveResume(resumeId, resumeDTO);
            return new ResponseEntity<>(HttpStatus.OK); // 이력서 저장 후 200 반환
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 서버 에러 발생 시 500 반환
        }
    }

    // 이력서 데이터 불러오기
    @GetMapping("/{resumeId}")
    public ResponseEntity<ResumeDTO> getResumeByResumeId(@PathVariable int resumeId) {
        try {
            ResumeDTO resumeDTO = resumeService.getResumeByResumeId(resumeId);
            if (resumeDTO != null) {
                return new ResponseEntity<>(resumeDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // User 의 Resume 목록 조회
    @GetMapping
    public ResponseEntity<List<ResumeDTO>> getAllResumesByUser(@AuthenticationPrincipal String userId) {
        try {
            List<ResumeDTO> resumes = resumeService.getAllResumesByUser(userId);
            return new ResponseEntity<>(resumes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ResumeId 생성 : SkillId x3 생성
    @PostMapping
    public ResponseEntity<Resume> createResume(@RequestBody String title, @AuthenticationPrincipal String userId) {
        try {
            Resume createdResume = resumeService.createResume(title, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdResume);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ResumeId 삭제
    @DeleteMapping("/{resumeId}")
    public ResponseEntity<Void> deleteResumeByResumeId(@PathVariable int resumeId) {
        try {
            resumeService.deleteResumeByResumeId(resumeId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // ResumeId 업데이트 : Title만
    @PutMapping("/{resumeId}/title")
    public ResponseEntity<ResumeDTO> saveResumeTitleByResumeId(
            @PathVariable int resumeId,
            @RequestBody String newTitle) {
        try {
            ResumeDTO updatedResume = resumeService.saveResumeTitleByResumeId(resumeId, newTitle);
            if (updatedResume != null) {
                return ResponseEntity.ok(updatedResume);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
