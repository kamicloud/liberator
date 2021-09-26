package com.kamicloud.liberator.generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kamicloud.liberator.utils.UrlUtil;
import definitions.annotations.Request;
import com.kamicloud.liberator.stubs.core.OutputStub;
import com.kamicloud.liberator.stubs.core.TemplateStub;
import com.kamicloud.liberator.stubs.postman.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class PostmanGenerator extends BaseGenerator {
    private File outputPath;


    @Override
    public String getName() {
        return "postman";
    }

    @Override
    public void run() {
        outputPath = new File(Objects.requireNonNull(env.getProperty("generator.generators.postman.path")) + "/API Generator.postman_collection.json");

        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            PostmanStub postmanStub = postmanOutput(output);

            output.getTemplates().forEach((version, templateStub) -> {
            });

            String jsonString = gson.toJson(postmanStub);

            FileOutputStream fileOutputStream = new FileOutputStream(outputPath.getAbsolutePath());
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
            outputStreamWriter.write(jsonString);

            outputStreamWriter.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PostmanStub postmanOutput(OutputStub outputStub) {
        PostmanStub postmanStub = new PostmanStub();

        outputStub.getTemplates().forEach((version, templateStub) -> {
            PostmanItemStub templateOutput = new PostmanItemStub(version);
            postmanStub.addItem(templateOutput);

            templateOutput(templateOutput, version, templateStub);
        });

        return postmanStub;
    }

    private void templateOutput(PostmanItemStub postmanStub, String version, TemplateStub templateStub) {
        PostmanItemStub restfulItemStub = new PostmanItemStub("RESTFul");

        templateStub.getModels().forEach(modelStub -> {
            if (modelStub.isResource()) {
                PostmanItemStub restfulOne = new PostmanItemStub(modelStub.getName() + " " + (modelStub.getComment() == null ? "" : modelStub.getComment()));

                restfulItemStub.addItem(restfulOne);

                PostmanItemStub indexStub = new PostmanItemStub("Index");
                PostmanItemStub storeStub = new PostmanItemStub("Store");
                PostmanItemStub updateStub = new PostmanItemStub("Update");
                PostmanItemStub showStub = new PostmanItemStub("Show");
                PostmanItemStub destroyStub = new PostmanItemStub("Destroy");

                restfulOne.addItem(indexStub);
                restfulOne.addItem(storeStub);
                restfulOne.addItem(updateStub);
                restfulOne.addItem(showStub);
                restfulOne.addItem(destroyStub);

                PostmanItemRequestStub indexRequest = new PostmanItemRequestStub();
                PostmanItemRequestStub storeRequest = new PostmanItemRequestStub();
                PostmanItemRequestStub updateRequest = new PostmanItemRequestStub();
                PostmanItemRequestStub showRequest = new PostmanItemRequestStub();
                PostmanItemRequestStub destroyRequest = new PostmanItemRequestStub();

                indexStub.setRequest(indexRequest);
                storeStub.setRequest(storeRequest);
                updateStub.setRequest(updateRequest);
                showStub.setRequest(showRequest);
                destroyStub.setRequest(destroyRequest);

                indexRequest.setMethod("GET");
                indexRequest.setUrl(new PostmanItemRequestUrlStub(new String[]{
                    "{{host}}"
                }, new String[]{
                    UrlUtil.getUrlPrefix(true),
                    UrlUtil.transformVersion(version),
                    "restful",
                    UrlUtil.transformAction(modelStub.getName())
                }));

                storeRequest.setMethod("PUT");
                storeRequest.setUrl(new PostmanItemRequestUrlStub(new String[]{
                    "{{host}}"
                }, new String[]{
                    UrlUtil.getUrlPrefix(true),
                    UrlUtil.transformVersion(version),
                    "restful",
                    UrlUtil.transformAction(modelStub.getName())
                }));

                showRequest.setMethod("GET");
                showRequest.setUrl(new PostmanItemRequestUrlStub(new String[]{
                    "{{host}}"
                }, new String[]{
                    UrlUtil.getUrlPrefix(true),
                    UrlUtil.transformVersion(version),
                    "restful",
                    UrlUtil.transformAction(modelStub.getName()),
                    "1"
                }));

                updateRequest.setMethod("PATCH");
                updateRequest.setUrl(new PostmanItemRequestUrlStub(new String[]{
                    "{{host}}"
                }, new String[]{
                    UrlUtil.getUrlPrefix(true),
                    UrlUtil.transformVersion(version),
                    "restful",
                    UrlUtil.transformAction(modelStub.getName()),
                    "1"
                }));

                destroyRequest.setMethod("DELETE");
                destroyRequest.setUrl(new PostmanItemRequestUrlStub(new String[]{
                    "{{host}}"
                }, new String[]{
                    UrlUtil.getUrlPrefix(true),
                    UrlUtil.transformVersion(version),
                    "restful",
                    UrlUtil.transformAction(modelStub.getName()),
                    "1"
                }));


            }
        });

        postmanStub.addItem(restfulItemStub);

        templateStub.getControllers().forEach(controller -> {
            PostmanItemStub postmanItemStub = new PostmanItemStub(controller.getName() + " " + (controller.getComment() == null ? "" : controller.getComment()));
            postmanStub.addItem(postmanItemStub);

            controller.getActions().forEach((action) -> {
                String actionName = action.getName();

                PostmanItemStub actionStub = new PostmanItemStub(actionName + " " + (action.getComment() == null ? "" : action.getComment()));
                postmanItemStub.addItem(actionStub);

                //
                PostmanItemRequestStub postmanItemRequestStub = new PostmanItemRequestStub();
                actionStub.setRequest(postmanItemRequestStub);

                postmanItemRequestStub.setDescription(action.getComment());

                // 传输数据
                PostmanItemRequestBodyStub postmanItemRequestBodyStub = new PostmanItemRequestBodyStub();
                postmanItemRequestStub.setBody(postmanItemRequestBodyStub);

                PostmanItemRequestUrlStub postmanItemRequestUrlStub = new PostmanItemRequestUrlStub();
                postmanItemRequestStub.setUrl(postmanItemRequestUrlStub);

                postmanItemRequestUrlStub.addHost("{{host}}");
                postmanItemRequestUrlStub.addPath(UrlUtil.getUrlPrefix(true));
                postmanItemRequestUrlStub.addPath(UrlUtil.transformVersion(version));
                postmanItemRequestUrlStub.addPath(UrlUtil.transformController(controller.getName()));
                postmanItemRequestUrlStub.addPath(UrlUtil.transformAction(action.getName()));

                postmanItemRequestBodyStub.addParameter(new PostmanParameterStub("__test_mode", "1"));
                postmanItemRequestBodyStub.addParameter(new PostmanParameterStub("__user", ""));

                action.getRequests().forEach((parameter) -> {
                    if (parameter.hasAnnotation(Request.class)) {
                        PostmanParameterStub postmanParameterStub = new PostmanParameterStub(parameter.getName());
                        String comment = parameter.getComment();
                        if (comment != null) {
                            postmanParameterStub.setDescription(comment);
                        }
                        postmanItemRequestBodyStub.addParameter(postmanParameterStub);
                    }
                });
            });
        });
    }
}
