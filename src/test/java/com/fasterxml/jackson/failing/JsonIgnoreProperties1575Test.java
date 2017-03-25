package com.fasterxml.jackson.failing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.databind.*;

public class JsonIgnoreProperties1575Test extends BaseMapTest
{
    static class Person {
        public String name;

        @JsonProperty("person_z") // renaming this to person_p works
        @JsonIgnoreProperties({"person_z"}) // renaming this to person_p works
//        public Set<Person> personZ;
        public Person personZ;
    }

    public void testIgnorePropDeser1575() throws Exception
    {
        String st = aposToQuotes("{ 'name': 'admin',\n"
//                + "    'person_z': [ { 'name': 'admin' } ]"
              + "    'person_z': { 'name': 'admin' }"
                + "}");

        ObjectMapper mapper = new ObjectMapper();
        Person result = mapper.readValue(st, Person.class);
        assertEquals("admin", result.name);
    }

    /*
    public void testIgnorePropSer1575() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        Person input = new Person();
        input.name = "Bob";

        // 24-Mar-2017, tatu: This shouldn't cause issues... but does as of now:
        
//        input.personZ = input;
        String json = mapper.writeValueAsString(input);
        assertNotNull(json);
    }
    */
}
