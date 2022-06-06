package com.ciberciti.notes.utils;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class InputValidatorTest {

    InputValidator SUT;

    @Before
    public void setUp() throws Exception {
        SUT = new InputValidator();
    }

    @Test
    public void validateIndianMobileNumber_moreThan10_falseReturned() {
        boolean result = SUT.validateIndianMobileNumber("986100028233214351513515");
        Assert.assertThat(result, is(false));
    }

    @Test
    public void validateIndianMobileNumber_lessThan10_falseReturned() {
        boolean result = SUT.validateIndianMobileNumber("986100");
        Assert.assertThat(result, is(false));
    }

    @Test
    public void validateIndianMobileNumber_containsAlphabet_falseReturned() {
        boolean result = SUT.validateIndianMobileNumber("986a100076");
        Assert.assertThat(result, is(false));
    }

    @Test
    public void validateIndianMobileNumber_nonIndianNumber_falseReturned() {
        boolean result = SUT.validateIndianMobileNumber("1986910007");
        Assert.assertThat(result, is(false));
    }

    @Test
    public void validateIndianMobileNumber_properIndianNumber_trueReturned() {
        boolean result = SUT.validateIndianMobileNumber("9519197224");
        Assert.assertThat(result, is(true));

    }

    @Test
    public void validateEmailAddress_withoutAtSymbol_falseReturned() {
        boolean result = SUT.validateEmailAddress("patelabhishekgmail.com");
        Assert.assertThat(result, is(false));
        Assert.assertThat(SUT.validateEmailAddress("notanemail.com"), is(false));
        Assert.assertThat(SUT.validateEmailAddress("asfnkjna.com"), is(false));
        Assert.assertThat(SUT.validateEmailAddress("notasafkjakjnnemail.com"), is(false));
    }

    @Test
    public void validateEmailAddress_domainLengthLessThan4_falseReturned() {
        boolean result = SUT.validateEmailAddress("patelabhishek2007@ku.com");
        Assert.assertThat(result, is(false));
        Assert.assertThat(SUT.validateEmailAddress("notworking@1.com"), is(false));
    }


    @Test
    public void validateEmailAddress_nameLengthLessThan4_falseReturned() {
        boolean result = SUT.validateEmailAddress("pat@gmail.com");
        Assert.assertThat(result, is(false));


    }

    @Test
    public void validateEmailAddress_properEmail_trueReturned() {
        boolean result = SUT.validateEmailAddress("patelabhishek2007@gmail.com");
        Assert.assertThat(result, is(true));
        Assert.assertThat(SUT.validateEmailAddress("workingexaMmple@email.com"), is(true));
        Assert.assertThat(SUT.validateEmailAddress("greataMn@email.com"), is(true));
    }


    @Test
    public void validatePassword_lessThan8_falseReturned() {
        boolean result = SUT.validatePassword("aAA@22", "name");
        Assert.assertThat(result, is(false));
        Assert.assertThat(SUT.validatePassword("bBB34&", "someName"), is(false));
    }

    @Test
    public void validatePassword_moreThan15_falseReturned() {
        boolean result = SUT.validatePassword("AAbhjafbj@289wakjskalskls2", "name");
        Assert.assertThat(result, is(false));
        Assert.assertThat(SUT.validatePassword("BB3randompas@74&", "someName"), is(false));
    }

    @Test
    public void validatePassword_containName_falseReturned() {
        boolean result = SUT.validatePassword("AA@22name", "name");
        Assert.assertThat(result, is(false));
        Assert.assertThat(SUT.validatePassword("BB3someName4&", "someName"), is(false));
    }

    @Test
    public void validatePassword_firstCharCapital_falseReturned() {
        boolean result = SUT.validatePassword("AA@22name", "name");
        Assert.assertThat(result, is(false));
        Assert.assertThat(SUT.validatePassword("BB3someName4&", "someName"), is(false));
    }

    @Test
    public void validatePassword_lessThan2Capital_falseReturned() {
        boolean result = SUT.validatePassword("A@22name", "name");
        Assert.assertThat(result, is(false));
        Assert.assertThat(SUT.validatePassword("3someame4&", "someName"), is(false));
    }

    @Test
    public void validatePassword_lessThan2digit_falseReturned() {
        boolean result = SUT.validatePassword("A@2ankjnAAname", "name");
        Assert.assertThat(result, is(false));
        Assert.assertThat(SUT.validatePassword("someamesjefihw@AA&", "someName"), is(false));
    }

    @Test
    public void validatePassword_NoSpecialChars_falseReturned() {
        boolean result = SUT.validatePassword("A22ankjnAAname", "name");
        Assert.assertThat(result, is(false));
        Assert.assertThat(SUT.validatePassword("someamesjefihwAA", "someName"), is(false));
    }

    @Test
    public void validatePassword_ProperPassword_trueReturned() {
        boolean result = SUT.validatePassword("aA22ankjn@22Ae", "name");
        Assert.assertThat(result, is(true));
        Assert.assertThat(SUT.validatePassword("somBB&&333jefi", "someName"), is(true));
    }



}