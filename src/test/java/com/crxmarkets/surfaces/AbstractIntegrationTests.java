package com.crxmarkets.surfaces;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import net.minidev.json.JSONValue;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.test.web.servlet.MvcResult;

/**
 * This class has a commons methods to use on Integration Tests
 *
 * @author José Carlos Mazella Junior
 * @version 1.0 15/04/2019
 */
public abstract class AbstractIntegrationTests {

    /**
     * This method return {@link MvcResult} content as string
     *
     * @param mvcResult
     *         object of result call
     *
     * @return response content as {@link String}
     *
     * @throws UnsupportedEncodingException
     *         can be throw parsing content to string
     */
    protected String getMvcResultAsString(final MvcResult mvcResult) throws UnsupportedEncodingException {
        return mvcResult.getResponse().getContentAsString();
    }

    /**
     * This method return a file as string
     *
     * @param nameFile
     *         with folder starting without /
     *
     * @return file content as {@link String}
     *
     * @throws UnsupportedEncodingException
     *         can be throw parsing content to string
     */
    protected String getJsonFileAsString(final String nameFile) throws UnsupportedEncodingException {
        final InputStream resourceAsStream = getClass().getResourceAsStream(String.format("/%s", nameFile));
        return JSONValue.parse(new InputStreamReader(resourceAsStream, "UTF-8")).toString();
    }

    /**
     * This method will get a volume value on response of mvc result and return It as {@link Integer}
     *
     * @param mvcResult
     *         result from mock mvc request
     *
     * @return a integer number from json object
     *
     * @throws Exception
     *         can be throw getting content of json object
     */
    protected Integer getVolumeValueFromMvcResult(final MvcResult mvcResult) throws Exception {
        final JSONObject jsonObject = getJsonObjectFromMvcResult(mvcResult);
        return jsonObject.getInt("volume");
    }

    private JSONObject getJsonObjectFromMvcResult(final MvcResult mvcResult) throws UnsupportedEncodingException, JSONException {
        final String contentAsString = mvcResult.getResponse().getContentAsString();
        return new JSONObject(contentAsString);
    }

}
