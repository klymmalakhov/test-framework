package utils;

/**
 * In this class we collect end-points which are using during the testing.
 * The property file is not relevant to use because end-points can be modified (user key)
 */

public class EndpointUrl {

    //Global
    private final static String API_KEY = "/api/json/v1/1/";

    //search
    public final static String SEARCH = API_KEY + "search.php";

    //look up
    public final static String LOOKUP = API_KEY + "lookup.php";

}
