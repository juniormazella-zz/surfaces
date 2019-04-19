package com.crxmarkets.surfaces;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles({"test"})
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.JVM)
@AutoConfigureMockMvc
public class SurfaceControllerTests extends AbstractIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void Given_OmissionConfigurationOfSurface__When__GetVolume_Then_ExpectErrorWithStatusBadRequest() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/surfaces/volumes")).andExpect(status().isBadRequest()).andReturn();
        JSONAssert.assertEquals(getJsonFileAsString("expected/expected_without_configuration.json"), getMvcResultAsString(mvcResult), true);
    }

    @Test
    public void Given_ConfigurationOfSurfaceWithNumberAreNotInteger_When_GetVolume_Then_ExpectErrorWithStatusBadRequest() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/surfaces/volumes").param("configuration", "3.12,2.12,4.32"))
                .andExpect(status().isBadRequest()).andReturn();
        JSONAssert.assertEquals(getJsonFileAsString("expected/expected_with_invalid_configuration.json"), getMvcResultAsString(mvcResult), true);
    }

    @Test
    public void Given_EmptyConfigurationOfSurface_When_GetVolume_Then_ExpectErrorWithStatusCodeBadRequest() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/surfaces/volumes").param("configuration", "")).andExpect(status().isBadRequest())
                .andReturn();
        JSONAssert.assertEquals(getJsonFileAsString("expected/expected_with_empty_configuration.json"), getMvcResultAsString(mvcResult), true);
    }

    @Test
    public void Given_ConfigurationOfSurfaceWithInvalidValues_When_GetVolume_Then_ExpectErrorWithStatusBadRequest() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/surfaces/volumes").param("configuration", "one,two,four,seven"))
                .andExpect(status().isBadRequest()).andReturn();
        JSONAssert.assertEquals(getJsonFileAsString("expected/expected_with_invalid_parameter_type_configuration.json"),
                getMvcResultAsString(mvcResult), true);
    }

    @Test
    public void Given_ConfigurationOfSurfaceWhereAllNumberAreEqual_When_GetVolume_Then_ExpectZeroAsResult() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/surfaces/volumes").param("configuration", "4,4,4,4,4")).andExpect(status().isOk())
                .andReturn();
        final Integer volume = getVolumeValueFromMvcResult(mvcResult);
        Assert.assertEquals(new Integer(0), volume);
    }

    @Test
    public void Given_InconsistentConfigurationOfSurface_When_GetVolume_Then_ExpectZeroAsResult() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/surfaces/volumes").param("configuration", "6,5,4,3,2,1")).andExpect(status().isOk()).andReturn();
        Assert.assertEquals(new Integer(0), getVolumeValueFromMvcResult(mvcResult));

        mvcResult = mockMvc.perform(get("/surfaces/volumes").param("configuration", "1,2,3,4,5,6")).andExpect(status().isOk()).andReturn();
        Assert.assertEquals(new Integer(0), getVolumeValueFromMvcResult(mvcResult));
    }

    @Test
    public void Given_ValidConfigurationWithTwoFillablseHoles_When_GetVolume_Then_ExpectTwoAsResult() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/surfaces/volumes").param("configuration", "3,2,4,1,2")).andExpect(status().isOk())
                .andReturn();
        final Integer volume = getVolumeValueFromMvcResult(mvcResult);
        Assert.assertEquals(new Integer(2), volume);
    }

    @Test
    public void Given_ValidConfigurationWithEightFillablesHoles__When__GetVolume_Then_ExpectEightAsResult() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/surfaces/volumes").param("configuration", "4,1,1,0,2,3")).andExpect(status().isOk())
                .andReturn();
        final Integer volume = getVolumeValueFromMvcResult(mvcResult);
        Assert.assertEquals(new Integer(8), volume);
    }

    @Test
    public void Given_ValidConfigurationWithThreeFillablesHoles_When_GetVolume_Then_ExpectThreeAsResult() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/surfaces/volumes").param("configuration", "0,2,0,0,0,1,0")).andExpect(status().isOk())
                .andReturn();
        final Integer volume = getVolumeValueFromMvcResult(mvcResult);
        Assert.assertEquals(new Integer(3), volume);
    }

    @Test
    public void Given_ValidConfigurationWithFourFillablesHoles_When_GetVolume_Then_ExpectFourAsResult() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/surfaces/volumes").param("configuration", "3,5,4,5,3,4,2,2,3")).andExpect(status().isOk())
                .andReturn();
        final Integer volume = getVolumeValueFromMvcResult(mvcResult);
        Assert.assertEquals(new Integer(4), volume);
    }

    @Test
    public void Given_ValidConfigurationWithNineFillablesHoles_When_GetVolume_Then_ExpectNineAsResult() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/surfaces/volumes").param("configuration", "2,2,4,4,2,2,4,4,2,2,4,3,5"))
                .andExpect(status().isOk()).andReturn();
        final Integer volume = getVolumeValueFromMvcResult(mvcResult);
        Assert.assertEquals(new Integer(9), volume);
    }

    @Test
    public void Given_ConfigurationOfSurfaceWithOnlyOneValue_When_GetVolume_Then_ExpectZeroAsResult() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/surfaces/volumes").param("configuration", "4")).andExpect(status().isOk()).andReturn();
        final Integer volume = getVolumeValueFromMvcResult(mvcResult);
        Assert.assertEquals(new Integer(0), volume);
    }

    @Test
    public void Given_ConfigurationOfSurfaceWithSixteenFillablesHoles_When_GetVolume_ExpectSixteemAsResult() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/surfaces/volumes").param("configuration", "4,0,0,4,2,1,1,2,3,1,3"))
                .andExpect(status().isOk()).andReturn();
        final Integer volume = getVolumeValueFromMvcResult(mvcResult);
        Assert.assertEquals(new Integer(16), volume);
    }

    @Test
    public void Given_ConfigurationOfSurfaceWithFifteenFillablesHoles_When_GetVolume_ExpectFifteenAsResult() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/surfaces/volumes").param("configuration", "4,0,0,4,2,1,1,2,3,1,2"))
                .andExpect(status().isOk()).andReturn();
        final Integer volume = getVolumeValueFromMvcResult(mvcResult);
        Assert.assertEquals(new Integer(15), volume);
    }

    @Test
    public void Given_ConfigurationOfSurfaceWithSevenFillablesHoles_When_GetVolume_ExpectSevenAsResult() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/surfaces/volumes").param("configuration", "0,1,1,3,2,1,1,2,3,1,2"))
                .andExpect(status().isOk()).andReturn();
        final Integer volume = getVolumeValueFromMvcResult(mvcResult);
        Assert.assertEquals(new Integer(7), volume);
    }

    @Test
    public void Given_ConfigurationOfSurfaceWithFourFillablesHoles_When_GetVolume_ExpectFourAsResult() throws Exception {
        final MvcResult mvcResult = mockMvc.perform(get("/surfaces/volumes").param("configuration", "0,1,0,2,3,4,3,1,1,2,2,0,1"))
                .andExpect(status().isOk()).andReturn();
        final Integer volume = getVolumeValueFromMvcResult(mvcResult);
        Assert.assertEquals(new Integer(4), volume);
    }

}
