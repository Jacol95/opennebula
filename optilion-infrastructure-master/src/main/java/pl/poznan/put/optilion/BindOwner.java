package pl.poznan.put.optilion;

import java.util.List;

import pl.poznan.put.optilion.dbconnector.Services;
import pl.poznan.put.optilion.exception.OptFileNotFoundException;
import pl.poznan.put.optilion.model.OptFile;
import pl.poznan.put.optilion.model.OptTest;
import pl.poznan.put.optilion.model.ProblemDefinition;
import pl.poznan.put.optilion.model.ProblemInstance;
import pl.poznan.put.optilion.model.Run;
import pl.poznan.put.optilion.service.OptFileService;
import pl.poznan.put.optilion.service.OptTestService;
import pl.poznan.put.optilion.service.ProblemDefinitionService;
import pl.poznan.put.optilion.service.ProblemInstanceService;
import pl.poznan.put.optilion.service.RunService;

public class BindOwner {
    private ProblemDefinitionService problemService  = Services.getProblemDefinitionService();
    private ProblemInstanceService   instanceService = Services.getProblemInstanceService();
    private OptTestService           testService     = Services.getTestService();
    private RunService               runService      = Services.getRunService();
    private OptFileService           optFileService  = Services.getFileService();

    public void bind() {
        bindProblemFiles();
        bindInstanceFiles();
        bindTestFiles();
        bindRunFiles();
    }

    private void bindProblemFiles() {
        List<ProblemDefinition> problems = problemService.findAll();
        for (ProblemDefinition pd : problems) {
            for (OptFile attachment : pd.getAttachments()) {
                attachment.setOwner(pd.getUser());
                try {
                    optFileService.update(attachment);
                } catch (OptFileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            OptFile judge = pd.getProblemJudge();
            judge.setOwner(pd.getUser());
            try {
                optFileService.update(judge);
            } catch (OptFileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void bindInstanceFiles() {
        List<ProblemInstance> instances = instanceService.findAll();

        for (ProblemInstance i : instances) {
            OptFile input = i.getInputFile();
            OptFile output = i.getOutputFile();
            input.setOwner(i.getProblemDefinition().getUser());
            output.setOwner(i.getProblemDefinition().getUser());
            try {
                optFileService.update(output);
                optFileService.update(input);
            } catch (OptFileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void bindTestFiles() {
        List<Run> runs = runService.findAll();

        for (Run run : runs) {
            List<OptTest> tests = testService.findByRunId(run.getId());
            for (OptTest test : tests) {
                OptFile out = test.getOutputFile();
                OptFile err = test.getErrputFile();

                out.setOwner(test.getRun().getUser());
                err.setOwner(test.getRun().getUser());

                try {
                    optFileService.update(err);
                    optFileService.update(out);
                } catch (OptFileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void bindRunFiles() {
        List<Run> runs = runService.findAll();

        for (Run r : runs) {
            OptFile source = r.getSourceFile();
            OptFile log = r.getCompilationLog();

            source.setOwner(r.getUser());
            log.setOwner(r.getUser());

            try {
                optFileService.update(source);
                optFileService.update(log);
            } catch (OptFileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
