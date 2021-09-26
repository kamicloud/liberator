package com.kamicloud.liberator.generators;

import com.kamicloud.liberator.stubs.core.OutputStub;
import com.kamicloud.liberator.utils.UrlUtil;

import java.io.*;

public class TestCaseGenerator extends BaseGenerator {

    private File outputDir;

    @Override
    public String getName() {
        return "testcases";
    }

    @Override
    public void run() {
        this.outputDir = new File(env.getProperty("generator.generators.testcases.path", ""));
        writeTestCases(output);
    }

    private void writeTestCases(OutputStub outputStub) {
        outputStub.getTemplates().forEach((version, template) -> {
            template.getControllers().forEach(controllerStub -> {
                controllerStub.getActions().forEach((actionStub) -> {
                    try {
                        String actionName = actionStub.getName();
                        String path = "/" + version + "/" + controllerStub.getName() + "/" + actionName;
                        String url = UrlUtil.getUrlWithPrefix(version, controllerStub.getName(), actionName);
                        File file = new File(outputDir.getAbsolutePath() + path + ".yml");
                        file.getParentFile().mkdirs();
                        if (file.exists()) {
                            return;
                        }

                        file.createNewFile();

                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

                        outputStreamWriter.write("# __api: /api" + url + "\n");
                        outputStreamWriter.write("__enabled: false\n");
//                        outputStreamWriter.write("# __version:\n");
//                        outputStreamWriter.write("__controller: " + controllerStub.getName() + "\n");
//                        outputStreamWriter.write("__action: " + actionName + "\n");
                        outputStreamWriter.write("__role:\n");
                        outputStreamWriter.write("__user:\n");
                        outputStreamWriter.write("__anchor:\n");
                        outputStreamWriter.write("__params:\n");

                        actionStub.getRequests().forEach((requestStub) -> {
                            try {
                                String requestName = requestStub.getName();
                                outputStreamWriter.write("  " + requestName + ":\n");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        outputStreamWriter.write("# __testcases:\n");
                        outputStreamWriter.write("\n");


                        outputStreamWriter.close();
                        fileOutputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            });
        });
    }
}
