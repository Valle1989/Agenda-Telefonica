package com.challenge.java.agenda.util.docs;

public interface PersonConstantDocs {

    public static final String PERSON = "Person Docs";

    public static final String PERSON_READ_ALL_PEOPLE = "Returns a paginated list of people";
    public static final String PERSON_FIND_ALL = "Returns a list of people";
    public static final String PERSON_FIND_BY_ID = "Returns a person through id parameter";
    public static final String PERSON_ADD_PERSON = "Create new person";
    public static final String PERSON_BY_NAME = "Get a person by name";
    public static final String PERSON_DELETE_PERSON = "Delete a person";

    public static final String PERSON_GET_200_OK = "Person found";
    public static final String PERSON_GET_404_NOT_FOUND = "Page number not found";

    public static final String PERSON_POST_201_CREATED = "The person has been created";

    public static final String PERSON_DELETE_200_OK = "The person has been removed";
    public static final String PERSON_DELETE_403_FORBIDDEN = "Access denied to delete this person";

    public static final String PERSON_404_NOT_FOUND = "Cannot find any person";

    public static final String PERSON_DELETE_PARAM_ID = "Enter an existing id to remove a person";

    public static final String PERSON_POST_PARAM_PERSON_REQUEST_DTO = "Fill in the required fields to save";

    public static final String PERSON_GET_PARAM_ID = "Enter an existing id to search person";

    public static final String PERSON_GET_PARAM_PAGE_NUMBER = "Enter an existing number page to search people";

    public static final String PERSON_DTO_MODEL = "Model to create a person";
    public static final String PERSON_DTO_MODEL_FIELD_NAME = "Choose a name for the person";
    public static final String PERSON_DTO_MODEL_FIELD_PHONE = "Enter the person's phone number";
}
