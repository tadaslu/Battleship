package tadas.luksas.client;

import org.json.simple.parser.ParseException;

public class Test {
    public static void main(String[] arg) throws ParseException {
        String str ="{\"columns\":[\"K\",\"I\",\"L\",\"O\",\"M\",\"E\",\"T\",\"R\",\"A\",\"S\"],\"rows\":[0,1,2,3,4,5,6,7,8,9],\"id\":\"5a37c7f25bb660407893a6fc\",\"nextTurnForUserId\":\"5a37c7a75bb660407893a6fb\",\"status\":\"READY_TO_PLAY\",\"winnerUserId\":\"\",\"events\":[{\"date\":1513606209010,\"coordinate\":{\"column\":\"I\",\"row\":2},\"userId\":\"5a37c7a75bb660407893a6fb\",\"hit\":false},{\"date\":1513606251579,\"coordinate\":{\"column\":\"I\",\"row\":2},\"userId\":\"5a3762425bb6602e10604721\",\"hit\":false},{\"date\":1513676358821,\"coordinate\":{\"column\":\"T\",\"row\":6},\"userId\":\"5a37c7a75bb660407893a6fb\",\"hit\":false},{\"date\":1513758476254,\"coordinate\":{\"column\":\"I\",\"row\":2},\"userId\":\"5a3762425bb6602e10604721\",\"hit\":false}]}";
        //new GameServiceImpl().convertGame(str);


    }
}
