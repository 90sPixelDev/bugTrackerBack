package dev._sPixelDev.bugTrackerAPI.Controller;

import dev._sPixelDev.bugTrackerAPI.Entity.Developers;
import dev._sPixelDev.bugTrackerAPI.Entity.HttpResponseHandler;
import dev._sPixelDev.bugTrackerAPI.Entity.Project;
import dev._sPixelDev.bugTrackerAPI.Repository.DeveloperRepo;
import dev._sPixelDev.bugTrackerAPI.Repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class ProjectsController {
    private ProjectRepo projectRepo;

    @Autowired
    public void setProjectRepo(ProjectRepo projectRepo) {this.projectRepo = projectRepo; }

    private DeveloperRepo developerRepo;

    @Autowired
    public void setDeveloperRepo(DeveloperRepo developerRepo) { this.developerRepo = developerRepo;}

    @GetMapping("/get/projects")
    public ResponseEntity<Object> getProjects (@RequestParam(defaultValue = "0") int pageNumber) {

        try {
            Page<Project> projects = projectRepo.findAll(PageRequest.of(pageNumber, 20, Sort.Direction.ASC, "projectId"));
            return HttpResponseHandler.generateResponse(HttpStatus.OK, false, "Data successfully received", projects.getContent());
        }
        catch (HttpClientErrorException e) {
            return HttpResponseHandler.generateResponse(e.getStatusCode(), true, e.getLocalizedMessage(), pageNumber);
        }
    };

    @PostMapping("add/project")
    public ResponseEntity<Object> getAllProjects(@RequestBody Project newProject) {

        try {
            newProject.setDateCreated(LocalDate.now());
            projectRepo.save(newProject);
            return HttpResponseHandler.generateResponse(HttpStatus.OK, false, "Successfully added new project info to database", newProject);
        }
        catch (HttpClientErrorException e){
            return HttpResponseHandler.generateResponse(e.getStatusCode(), true, e.getLocalizedMessage(), newProject);
        }
    };

    @PutMapping("update/project/{projectId}")
    public ResponseEntity<Object> updateProject(@PathVariable String projectId, @RequestBody Project projectData) {

        try {
            Project project = projectRepo.findById(Integer.valueOf(projectId)).get();
            if (!projectData.getProjectTitle().equals(project.getProjectTitle())) {
                project.setProjectTitle(projectData.getProjectTitle());
                projectRepo.save(project);
            }
            return HttpResponseHandler.generateResponse(HttpStatus.OK, false, "Successfully updated project info in database", project);
        }
        catch (HttpClientErrorException e){
            return HttpResponseHandler.generateResponse(e.getStatusCode(), true, e.getLocalizedMessage(), projectData);
        }
    };

    @PutMapping("update/project/{projectId}/{devId}")
    public ResponseEntity<Object> updateProjectAddDev(@PathVariable String projectId, @PathVariable String devId) {

        try {
            Project project = projectRepo.findById(Integer.valueOf(projectId)).get();
            Developers dev = developerRepo.findById(Integer.valueOf(devId)).get();

            project.getDevs().add(dev);
            projectRepo.save(project);

            return HttpResponseHandler.generateResponse(HttpStatus.OK, false, "Successfully updated project info in database", project);
        }
        catch (HttpClientErrorException e){
            return HttpResponseHandler.generateResponse(e.getStatusCode(), true, e.getLocalizedMessage(), null);
        }
    };

    @DeleteMapping("/delete/project/{projectId}")
    public ResponseEntity<Object> createNewDev (@PathVariable int projectId) {
        try {
            projectRepo.deleteById(projectId);
            return HttpResponseHandler.generateResponse(HttpStatus.OK, false, "Successfully deleted developer from database", null);
        }
        catch (HttpClientErrorException e) {
            return HttpResponseHandler.generateResponse(e.getStatusCode(), true, e.getLocalizedMessage(), null);
        }
    };

}
