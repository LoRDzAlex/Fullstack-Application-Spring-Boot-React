/*
package ch.wiss.unternehmensliste.service.job;

import ch.wiss.unternehmensliste.model.JobApplication;
import ch.wiss.unternehmensliste.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import ch.wiss.unternehmensliste.dto.JobDTO;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class JobServiceImpl extends JobService {

    private final JobApplicationRepository jobRepository;
    private final ModelMapper modelMapper;

    public JobServiceImpl(JobApplicationRepository jobRepository, ModelMapper modelMapper) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<JobDTO> getAllJobs() {
        List<JobApplication> jobs = (List<JobApplication>) jobRepository.findAll();
        return jobs.stream()
                .map(job -> modelMapper.map(job, JobDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public JobDTO getJobById(Long id) {
        JobApplication job = jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Job not found"));
        return modelMapper.map(job, JobDTO.class);
    }

    @Override
    public JobDTO createJob(JobDTO jobDTO) {
        Job job = modelMapper.map(jobDTO, Job.class);
        jobRepository.save(job);
        return modelMapper.map(job, JobDTO.class);
    }

    @Override
    public JobDTO updateJob(Long id, JobDTO jobDTO) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Job not found"));

        job.setTitle(jobDTO.getTitle());
        job.setDescription(jobDTO.getDescription());
        job.setSalary(jobDTO.getSalary());

        jobRepository.save(job);

        return modelMapper.map(job, JobDTO.class);
    }

    @Override
    public void deleteJob(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Job not found"));

        jobRepository.delete(job);
    }
}
*/

