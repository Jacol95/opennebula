package pl.poznan.put.optilion;

import java.sql.Timestamp;

import pl.poznan.put.optilion.dbconnector.Services;
import pl.poznan.put.optilion.enumerator.Language;
import pl.poznan.put.optilion.enumerator.ProblemType;
import pl.poznan.put.optilion.enumerator.RuntimeCode;
import pl.poznan.put.optilion.files.FileService;
import pl.poznan.put.optilion.model.OptFile;
import pl.poznan.put.optilion.model.OptTest;
import pl.poznan.put.optilion.model.ProblemDefinition;
import pl.poznan.put.optilion.model.ProblemInstance;
import pl.poznan.put.optilion.model.Role;
import pl.poznan.put.optilion.model.Run;
import pl.poznan.put.optilion.model.User;
import pl.poznan.put.optilion.model.enums.FileContext;
import pl.poznan.put.optilion.model.enums.FileType;
import pl.poznan.put.optilion.service.OptFileService;
import pl.poznan.put.optilion.service.OptTestService;
import pl.poznan.put.optilion.service.ProblemDefinitionService;
import pl.poznan.put.optilion.service.ProblemInstanceService;
import pl.poznan.put.optilion.service.RoleService;
import pl.poznan.put.optilion.service.RunService;
import pl.poznan.put.optilion.service.UserService;

public class InitDatabase {
    private RoleService              roleService              = Services.getRoleService();
    private UserService              userService              = Services.getUserService();
    private ProblemDefinitionService problemDefinitionService = Services.getProblemDefinitionService();
    private ProblemInstanceService   problemInstanceService   = Services.getProblemInstanceService();
    private OptTestService           optTestService           = Services.getTestService();
    private RunService               runService               = Services.getRunService();
    private OptFileService           optFileService           = Services.getFileService();

    public void init() {
        insertOptFiles();
        insertUsers();
        insertRoles();
        insertProblemDefinitions();
        insertprobleminstances();
        insertRuns();
        insertOptTests();
    }

    private void insertOptFiles() {
        insertOptFile(1, "a.cpp", "resources/optiliondata/submit/0/a.cpp", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(2, "sigfpe.py", "resources/optiliondata/submit/0/sigfpe.py", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(3, "SigFpe.java", "resources/optiliondata/submit/0/SigFpe.java", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(4, "SIGFPEcode.cpp", "resources/optiliondata/submit/0/SIGFPEcode.cpp", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(5, "sigsegv.py", "resources/optiliondata/submit/0/sigsegv.py", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(6, "SIGSEGVcode.cpp", "resources/optiliondata/submit/0/SIGSEGVcode.cpp", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(7, "sum.py", "resources/optiliondata/submit/0/sum.py", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(8, "Sum.java", "resources/optiliondata/submit/0/Sum.java", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(9, "testsum.cpp", "resources/optiliondata/submit/0/testsum.cpp", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(10, "tle.py", "resources/optiliondata/submit/0/tle.py", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(11, "Tle.java", "resources/optiliondata/submit/0/Tle.java", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(12, "TLEcode.cpp", "resources/optiliondata/submit/0/TLEcode.cpp", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);

        insertOptFile(13, "usersourcetest4.cpp", "resources/optiliondata/submit/13/usersourcetest4.cpp", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(14, "usersourcetest4.cpp", "resources/optiliondata/submit/14/usersourcetest4.cpp", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(15, "usersourcetest4.cpp", "resources/optiliondata/submit/15/usersourcetest4.cpp", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(16, "modACC.cpp", "resources/optiliondata/submit/16/modACC.cpp", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(17, "modWA.cpp", "resources/optiliondata/submit/17/modWA.cpp", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);

        insertOptFile(20, "usersourcetest1.cpp", "resources/optiliondata/submit/20/usersourcetest1.cpp", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(21, "usersourcetest2.cpp", "resources/optiliondata/submit/21/usersourcetest2.cpp", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(22, "usersourcetest3.cpp", "resources/optiliondata/submit/22/usersourcetest3.cpp", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(23, "usersourcetest4.cpp", "resources/optiliondata/submit/23/usersourcetest4.cpp", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(24, "usersourcetest5.cpp", "resources/optiliondata/submit/24/usersourcetest5.cpp", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);

        insertOptFile(30, "sumACC.cpp", "resources/optiliondata/submit/30/sumACC.cpp", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(31, "sumWA.cpp", "resources/optiliondata/submit/31/sumWA.cpp", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);

        insertOptFile(40, "CMake", "resources/optiliondata/script/versions/CMake.sh", FileType.PLAIN_TEXT, FileContext.VERSION_SCRIPT);
        insertOptFile(41, "G++", "resources/optiliondata/script/versions/g++.sh", FileType.PLAIN_TEXT, FileContext.VERSION_SCRIPT);
        insertOptFile(42, "GCC", "resources/optiliondata/script/versions/gcc.sh", FileType.PLAIN_TEXT, FileContext.VERSION_SCRIPT);
        insertOptFile(43, "Java", "resources/optiliondata/script/versions/Java.sh", FileType.PLAIN_TEXT, FileContext.VERSION_SCRIPT);
        insertOptFile(44, "Mcs", "resources/optiliondata/script/versions/mcs.sh", FileType.PLAIN_TEXT, FileContext.VERSION_SCRIPT);
        insertOptFile(45, "Python", "resources/optiliondata/script/versions/Python.sh", FileType.PLAIN_TEXT, FileContext.VERSION_SCRIPT);
        insertOptFile(46, "Python3", "resources/optiliondata/script/versions/Python3.sh", FileType.PLAIN_TEXT, FileContext.VERSION_SCRIPT);
        
        insertOptFile(50, "client.c", "resources/optiliondata/submit/70/client.c", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(51, "sum", "resources/optiliondata/submit/51/sum", FileType.BINARY, FileContext.USER_SOURCE);
        insertOptFile(52, "sum.py", "resources/optiliondata/submit/52/sum.py", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(53, "Sum.cs", "resources/optiliondata/submit/53/Sum.cs", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(54, "TSP.java", "resources/optiliondata/submit/54/TSP.java", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(55, "TSP.java", "resources/optiliondata/submit/55/TSP.java", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(56, "solution.tar.gz", "resources/optiliondata/submit/56/solution.tar.gz", FileType.BINARY, FileContext.USER_SOURCE);

        insertOptFile(57, "ModuloProblemJudge", "resources/optiliondata/judge/ModuloProblemJudge", FileType.BINARY, FileContext.JUDGE);
        insertOptFile(58, "SumProblemJudge", "resources/optiliondata/judge/SumProblemJudge", FileType.BINARY, FileContext.JUDGE);

        insertOptFile(60, "modOutputACC", "resources/optiliondata/submit/out/modOutputACC", FileType.PLAIN_TEXT, FileContext.PROBLEM_OUT);
        insertOptFile(61, "modOutputWA", "resources/optiliondata/submit/out/modOutputWA", FileType.PLAIN_TEXT, FileContext.PROBLEM_OUT);
        insertOptFile(62, "sumOutputACC", "resources/optiliondata/submit/out/sumOutputACC", FileType.PLAIN_TEXT, FileContext.PROBLEM_OUT);
        insertOptFile(63, "sumOutputWA", "resources/optiliondata/submit/out/sumOutputWA", FileType.PLAIN_TEXT, FileContext.PROBLEM_OUT);
        insertOptFile(64, "test1", "resources/optiliondata/submit/out/test1", FileType.PLAIN_TEXT, FileContext.PROBLEM_OUT);
        insertOptFile(65, "test2", "resources/optiliondata/submit/out/test2", FileType.PLAIN_TEXT, FileContext.PROBLEM_OUT);
        insertOptFile(66, "test3", "resources/optiliondata/submit/out/test3", FileType.PLAIN_TEXT, FileContext.PROBLEM_OUT);
        insertOptFile(67, "test4", "resources/optiliondata/submit/out/test4", FileType.PLAIN_TEXT, FileContext.PROBLEM_OUT);
        insertOptFile(68, "test5", "resources/optiliondata/submit/out/test5", FileType.PLAIN_TEXT, FileContext.PROBLEM_OUT);
        insertOptFile(69, "test6", "resources/optiliondata/submit/out/test6", FileType.PLAIN_TEXT, FileContext.PROBLEM_OUT);

        insertOptFile(70, "moduloInstance", "resources/optiliondata/probleminstance/moduloInstance", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(71, "moduloInstanceIE", "resources/optiliondata/probleminstance/moduloInstanceIE", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(72, "pr76.tsp", "resources/optiliondata/probleminstance/pr76.tsp", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(73, "pr107.tsp", "resources/optiliondata/probleminstance/pr107.tsp", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(74, "pr124.tsp", "resources/optiliondata/probleminstance/pr124.tsp", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(75, "pr136.tsp", "resources/optiliondata/probleminstance/pr136.tsp", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(76, "pr144.tsp", "resources/optiliondata/probleminstance/pr144.tsp", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(77, "pr152.tsp", "resources/optiliondata/probleminstance/pr152.tsp", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(78, "pr226.tsp", "resources/optiliondata/probleminstance/pr226.tsp", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(79, "pr264.tsp", "resources/optiliondata/probleminstance/pr264.tsp", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(80, "pr299.tsp", "resources/optiliondata/probleminstance/pr299.tsp", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(81, "pr439.tsp", "resources/optiliondata/probleminstance/pr439.tsp", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(82, "pr1002.tsp", "resources/optiliondata/probleminstance/pr1002.tsp", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(83, "pr2392.tsp", "resources/optiliondata/probleminstance/pr2392.tsp", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(84, "sumInstance", "resources/optiliondata/probleminstance/sumInstance", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(85, "testinstance", "resources/optiliondata/probleminstance/testinstance", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(86, "testinstanceForJudgeTSP2D", "resources/optiliondata/probleminstance/testinstanceForJudgeTSP2D", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);

        insertOptFile(91, "MineralJudge", "resources/optiliondata/judge/score", FileType.BINARY, FileContext.JUDGE);
        insertOptFile(92, "JudgeTSP2D", null, FileType.BINARY, FileContext.JUDGE);
        insertOptFile(93, "OneParameterFunctionJudge", "resources/optiliondata/judge/firstInteractiveJudge", FileType.BINARY, FileContext.JUDGE);

        insertOptFile(99, "null", null, FileType.PLAIN_TEXT, FileContext.NULL);

        insertOptFile(100, "GPL ver. 2 CE", "resources/optiliondata/license/GPL2CE", FileType.PLAIN_TEXT, FileContext.LICENCE);
        insertOptFile(101, "Apache License 2.0", "resources/optiliondata/license/AL2", FileType.PLAIN_TEXT, FileContext.LICENCE);
        insertOptFile(102, "Eclipse Public License ver. 1", "resources/optiliondata/license/EPL1", FileType.PLAIN_TEXT, FileContext.LICENCE);
        insertOptFile(103, "LGPL ver. 3", "resources/optiliondata/license/LGPL3", FileType.PLAIN_TEXT, FileContext.LICENCE);
        insertOptFile(104, "MIT", "resources/optiliondata/license/MIT", FileType.PLAIN_TEXT, FileContext.LICENCE);
        insertOptFile(105, "MPL ver. 2", "resources/optiliondata/license/MPL2", FileType.PLAIN_TEXT, FileContext.LICENCE);
        insertOptFile(106, "PostgreSQL", "resources/optiliondata/license/PSQL", FileType.PLAIN_TEXT, FileContext.LICENCE);

        insertOptFile(111, "Obrazek1", "resources/optiliondata/picture/1.png", FileType.JPG, FileContext.PICTURE);
        insertOptFile(112, "Obrazek2", "resources/optiliondata/picture/2.png", FileType.JPG, FileContext.PICTURE);

        insertOptFile(120, "mwater01.in", "resources/optiliondata/probleminstance/mwater01.in", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(121, "mwater02.in", "resources/optiliondata/probleminstance/mwater02.in", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(122, "mwater03.in", "resources/optiliondata/probleminstance/mwater03.in", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(123, "mwater04.in", "resources/optiliondata/probleminstance/mwater04.in", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(124, "mwater05.in", "resources/optiliondata/probleminstance/mwater05.in", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(125, "mwater06.in", "resources/optiliondata/probleminstance/mwater06.in", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(126, "mwater07.in", "resources/optiliondata/probleminstance/mwater07.in", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(127, "mwater08.in", "resources/optiliondata/probleminstance/mwater08.in", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(128, "mwater09.in", "resources/optiliondata/probleminstance/mwater09.in", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        insertOptFile(129, "mwater10.in", "resources/optiliondata/probleminstance/mwater10.in", FileType.PLAIN_TEXT, FileContext.PROBLEM_IN);
        
        insertOptFile(130, "1.zip", "resources/optiliondata/probleminstance/cec14/1.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(131, "2.zip", "resources/optiliondata/probleminstance/cec14/2.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(132, "3.zip", "resources/optiliondata/probleminstance/cec14/3.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(133, "4.zip", "resources/optiliondata/probleminstance/cec14/4.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(134, "5.zip", "resources/optiliondata/probleminstance/cec14/5.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(135, "6.zip", "resources/optiliondata/probleminstance/cec14/6.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(136, "7.zip", "resources/optiliondata/probleminstance/cec14/7.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(137, "8.zip", "resources/optiliondata/probleminstance/cec14/8.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(138, "9.zip", "resources/optiliondata/probleminstance/cec14/9.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(139, "10.zip", "resources/optiliondata/probleminstance/cec14/10.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(140, "11.zip", "resources/optiliondata/probleminstance/cec14/11.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(141, "12.zip", "resources/optiliondata/probleminstance/cec14/12.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(142, "13.zip", "resources/optiliondata/probleminstance/cec14/13.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(143, "14.zip", "resources/optiliondata/probleminstance/cec14/14.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(144, "15.zip", "resources/optiliondata/probleminstance/cec14/15.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(145, "16.zip", "resources/optiliondata/probleminstance/cec14/16.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(146, "17.zip", "resources/optiliondata/probleminstance/cec14/17.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(147, "18.zip", "resources/optiliondata/probleminstance/cec14/18.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(148, "19.zip", "resources/optiliondata/probleminstance/cec14/19.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(149, "20.zip", "resources/optiliondata/probleminstance/cec14/20.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(150, "21.zip", "resources/optiliondata/probleminstance/cec14/21.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(151, "22.zip", "resources/optiliondata/probleminstance/cec14/22.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(152, "23.zip", "resources/optiliondata/probleminstance/cec14/23.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(153, "24.zip", "resources/optiliondata/probleminstance/cec14/24.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(154, "25.zip", "resources/optiliondata/probleminstance/cec14/25.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(155, "26.zip", "resources/optiliondata/probleminstance/cec14/26.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(156, "27.zip", "resources/optiliondata/probleminstance/cec14/27.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(157, "28.zip", "resources/optiliondata/probleminstance/cec14/28.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(158, "29.zip", "resources/optiliondata/probleminstance/cec14/29.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        insertOptFile(159, "30.zip", "resources/optiliondata/probleminstance/cec14/30.zip", FileType.COMPRESSED, FileContext.PROBLEM_IN);
        
        insertOptFile(160, "CEC14", "resources/optiliondata/judge/cec14judge", FileType.BINARY, FileContext.JUDGE);
        insertOptFile(161, "cec_user_acc.cpp", "resources/optiliondata/submit/71/cec_user_acc.cpp", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        insertOptFile(162, "cec_user_tle.cpp", "resources/optiliondata/submit/73/cec_user_tle.cpp", FileType.PLAIN_TEXT, FileContext.USER_SOURCE);
        
        insertOptFile(163, "Polynomial", "resources/optiliondata/judge/polynomialTest", FileType.BINARY, FileContext.JUDGE);    
    }

    private void insertOptFile(Integer id, String name, String path, FileType type, FileContext context) {
        byte[] content = null;
        if (path != null) {
            content = FileService.readFileBytes(path);
        }
        User admin = userService.findById(1); // ADMIN
        OptFile optFile = new OptFile(name, content, admin, type, context);
        optFile.setId(id);
        optFileService.create(optFile);
    }

    private void insertUsers() {
        insertUser(1, "admin", "admin", "swag.huehuehue@gmail.com", "admin", "$2a$10$chQYAJFzPK6fliDbRm.eX.GVaMdv61Qmd3X7X9i5egNKNhz0AMud2", 0, true, false, false, false, 0.0, 0, null);
    }

    private void insertUser(Integer id, String firstName, String lastName, String email, String userName,
            String password, long expires, boolean accountEnabled, boolean accountExpired,
            boolean accountLocked, boolean credentialsExpired, Double standingScore, Integer problemsSolved,
            String activationDigest) {
        User user = new User();
        user.setId(id);
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setEmail(email);
        user.setUsername(userName);
        user.setPassword(password);
        user.setExpires(expires);
        user.setAccountEnabled(accountEnabled);
        user.setAccountExpired(credentialsExpired);
        user.setAccountLocked(accountLocked);
        user.setCredentialsExpired(credentialsExpired);
        user.setStandingScore(standingScore);
        user.setProblemsSolved(problemsSolved);
        user.setActivationDigest(activationDigest);
        userService.create(user);
    }

    private void insertRoles() {
        insertRole(1, "ROLE_USER", 1);
        insertRole(2, "ROLE_ADMIN", 1);
        insertRole(3, "ROLE_USER", 2);
    }

    private void insertRole(Integer id, String name, Integer userId) {
        Role role = new Role();

        User user = userService.findById(userId);

        role.setId(id);
        role.setAuthority(name);
        role.setUser(user);

        roleService.create(role);
    }

    private void insertProblemDefinitions() {
        insertProblemDefinition(1, "Sum", "SUM", "Add two integers.", 58, 1, ProblemType.SIMPLE, true);
        insertProblemDefinition(2, "TSP", "TSP", "Classical TSP problem.", 92, 1, ProblemType.SIMPLE, true);
        insertProblemDefinition(3, "Mod", "MOD", "Mod two integers.", 57, 1, ProblemType.SIMPLE, true);
        insertProblemDefinition(4, "Mineral Water", "MW",
                "***Mineral Water***\n\n I would like to found a company that will extract mineral water from underground sources, pour it out into bottles and sell it. To determine the best location for wells from which I will take the water I created a geological map of the land that I have. I divided it into many square fields and using advanced technology I determined the quality of the underground water that is below each square. The quality is described by the number from 0 to 255 inclusive (number describes the water polution - ie. the higher number denotes water with lower quality). An example map is visualized below. Each pixel represents one field. Fields with high quality are represented by green colour (the more intensive the colour is the better quality of the water) and fields with low quality are represented by red colour.\n\n![Mineral Water picture nr 1](/optilion/picture/111 \"Obrazek\")\n\nI can build several types of wells. Each well has its range radius r. The radius r means that when I build a well on the field with coordinates (x, y) then it will gather water from all fields that have Euclidean distance from this field less or equal to r. The profit of my company is in inverse proportion to the sum of water polution levels of fields that are in range of some well. Please help me determine the best location for wells to maximize my profit.\n\n***Input***\n\nThe first line of the input contains integer number 3 <= n <= 50 - the number of wells that I want to build. In the second line there are exactly n integer numbers denoting ranges of wells that I want to build. The third line contains two number 100 <= w, h <= 1000. Each of the next h lines contains w integer numbers 0 <= qi,j <= 255 denoting the quality of the water at point (i, j).\n\n***Example Input***\n\n3  \n10 30 40  \n640 480  \n20 23 26 23 17 22 21 28 25 24 …\n\n***Output***\n\nThe output should contain n lines. i-th line should consist of two numbers (x, y) - coordinates of the i-th well. The first number from the first line in the input is indexed as (0, 0), second number from the first line is (0, 1), etc. \n\n***Example output***\n\nNot necessarily the best output for the input presented above is following: \n629 300  115 142  465 255 \nThe visualization of the above output is presented below. Small circles represent location of wells and yellow circles theirs ranges.\n\n![obrazek](/optilion/picture/112 \"Obrazek\")",
                91, 1, ProblemType.SIMPLE, true);
        insertProblemDefinition(5, "Interactive", "IN", "Our first interactive problem", 93, 1, ProblemType.INTERACTIVE, true);
        
        insertProblemDefinition(6, "CEC14", "CEC", "CEC 2014 problem", 160, 1, ProblemType.INTERACTIVE, true);
        
        insertProblemDefinition(7, "Polynomial", "Polynomial", "Polynomial problem", 163, 1, ProblemType.INTERACTIVE, true);
    }

    private void insertProblemDefinition(Integer id, String name, String shortName, String description, Integer judgeId,
            Integer userId, ProblemType problemType, boolean active) {
        ProblemDefinition problemDefinition = new ProblemDefinition();

        OptFile judge = null;
        if (judgeId != null) {
            judge = optFileService.findById(judgeId);
        }
        User user = userService.findById(userId);

        problemDefinition.setId(id);
        problemDefinition.setName(name);
        problemDefinition.setShortName(shortName);
        problemDefinition.setDescription(description);
        problemDefinition.setProblemJudge(judge);
        problemDefinition.setUser(user);
        problemDefinition.setProblemType(problemType);
        problemDefinition.setActive(active);
        problemDefinitionService.create(problemDefinition);
    }

    private void insertprobleminstances() {
        insertprobleminstance(1, 84, 99, 1, 1, 4048676, 1, "na", false);
        insertprobleminstance(2, 71, 99, 1, 1, 4048676, 3, "na", false);
        insertprobleminstance(3, 72, 99, 1, 1, 4048676, 2, "na", false);
        insertprobleminstance(4, 73, 99, 1, 1, 4048676, 2, "na", false);
        insertprobleminstance(5, 74, 99, 1, 1, 4048676, 2, "na", false);
        insertprobleminstance(6, 75, 99, 1, 1, 4048676, 2, "na", false);
        insertprobleminstance(7, 76, 99, 1, 1, 4048676, 2, "na", false);
        insertprobleminstance(8, 77, 99, 1, 1, 4048676, 2, "na", false);
        insertprobleminstance(9, 78, 99, 1, 1, 4048676, 2, "na", false);
        insertprobleminstance(10, 79, 99, 1, 1, 4048676, 2, "na", false);
        insertprobleminstance(11, 80, 99, 1, 1, 4048676, 2, "na", false);
        insertprobleminstance(12, 81, 99, 1, 1, 4048676, 2, "na", false);
        insertprobleminstance(13, 82, 99, 1, 1, 4048676, 2, "na", false);
        insertprobleminstance(14, 83, 99, 1, 1, 4048676, 2, "na", false);
        insertprobleminstance(15, 70, 99, 1, 1, 4048676, 3, "na", false);
        insertprobleminstance(20, 120, 99, 10, 10, 1048576, 4, "na", false);
        insertprobleminstance(21, 121, 99, 10, 10, 1048576, 4, "na", false);
        insertprobleminstance(22, 122, 99, 10, 10, 1048576, 4, "na", false);
        insertprobleminstance(23, 123, 99, 10, 10, 1048576, 4, "na", false);
        insertprobleminstance(24, 124, 99, 10, 10, 1048576, 4, "na", false);
        insertprobleminstance(25, 125, 99, 10, 10, 1048576, 4, "na", false);
        insertprobleminstance(26, 126, 99, 10, 10, 1048576, 4, "na", false);
        insertprobleminstance(27, 127, 99, 10, 10, 1048576, 4, "na", false);
        insertprobleminstance(28, 128, 99, 10, 10, 1048576, 4, "na", false);
        insertprobleminstance(29, 129, 99, 10, 10, 1048576, 4, "na", false);
        
        insertprobleminstance(30, 93, 99, 10, 10, 1048576, 5, "na", false);
        
        insertprobleminstance(31, 130, 99, 1, 1, 4048676, 6, "1", true);
        insertprobleminstance(32, 131, 99, 1, 1, 4048676, 6, "2", true);
        insertprobleminstance(33, 132, 99, 1, 1, 4048676, 6, "3", true);
        insertprobleminstance(34, 133, 99, 1, 1, 4048676, 6, "4", true);
        insertprobleminstance(35, 134, 99, 1, 1, 4048676, 6, "5", true);
        insertprobleminstance(36, 135, 99, 1, 1, 4048676, 6, "6", true);
        insertprobleminstance(37, 136, 99, 1, 1, 4048676, 6, "7", true);
        insertprobleminstance(38, 137, 99, 1, 1, 4048676, 6, "8", true);
        insertprobleminstance(39, 138, 99, 1, 1, 4048676, 6, "9", true);
        insertprobleminstance(40, 139, 99, 1, 1, 4048676, 6, "10", true);
        insertprobleminstance(41, 140, 99, 1, 1, 4048676, 6, "11", true);
        insertprobleminstance(42, 141, 99, 1, 1, 4048676, 6, "12", true);
        insertprobleminstance(43, 142, 99, 1, 1, 4048676, 6, "13", true);
        insertprobleminstance(44, 143, 99, 1, 1, 4048676, 6, "14", true);
        insertprobleminstance(45, 144, 99, 1, 1, 4048676, 6, "15", true);
        insertprobleminstance(46, 145, 99, 1, 1, 4048676, 6, "16", true);
        insertprobleminstance(47, 146, 99, 1, 1, 4048676, 6, "17", true);
        insertprobleminstance(48, 147, 99, 1, 1, 4048676, 6, "18", true);
        insertprobleminstance(49, 148, 99, 1, 1, 4048676, 6, "19", true);
        insertprobleminstance(50, 149, 99, 1, 1, 4048676, 6, "20", true);
        insertprobleminstance(51, 150, 99, 1, 1, 4048676, 6, "21", true);
        insertprobleminstance(52, 151, 99, 1, 1, 4048676, 6, "22", true);
        insertprobleminstance(53, 152, 99, 1, 1, 4048676, 6, "23", true);
        insertprobleminstance(54, 153, 99, 1, 1, 4048676, 6, "24", true);
        insertprobleminstance(55, 154, 99, 1, 1, 4048676, 6, "25", true);
        insertprobleminstance(56, 155, 99, 1, 1, 4048676, 6, "26", true);
        insertprobleminstance(57, 156, 99, 1, 1, 4048676, 6, "27", true);
        insertprobleminstance(58, 157, 99, 1, 1, 4048676, 6, "28", true);
        insertprobleminstance(59, 158, 99, 1, 1, 4048676, 6, "29", true);
        insertprobleminstance(60, 159, 99, 1, 1, 4048676, 6, "30", true);
        
        insertprobleminstance(61, 99, 99, 1, 1, 4048676, 7, "1", false);
    }

    private void insertprobleminstance(Integer id, Integer inputFileId, Integer outputFileId,
            Integer timelimit, Integer timeout, Integer memout, Integer problemDefinitionId, String params, Boolean unpack) {
        ProblemInstance probleminstance = new ProblemInstance();
        OptFile inputFile = null;
        OptFile outputFile = null;
        if (inputFileId != null) {
            inputFile = optFileService.findById(inputFileId);
        }
        if (outputFileId != null) {
            outputFile = optFileService.findById(outputFileId);
        }
        ProblemDefinition problemDefinition = problemDefinitionService.findById(problemDefinitionId);

        probleminstance.setId(id);
        probleminstance.setTimeLimit(timelimit);
        probleminstance.setTimeout(timeout);
        probleminstance.setMemout(memout);
        probleminstance.setParams(params);
        
        probleminstance.setInputFile(inputFile);
        probleminstance.setOutputFile(outputFile);
        probleminstance.setProblemDefinition(problemDefinition);
        probleminstance.setUnpack(unpack);
        
        problemInstanceService.create(probleminstance);
    }

    private void insertRuns() {
        insertRun(20, 20, 99, "In Progress...", null, null, Language.CPP, 0, null, 2, 1);
        insertRun(21, 21, 99, "In Progress...", null, null, Language.CPP, 0, null, 2, 1);
        insertRun(22, 22, 99, "In Progress...", null, null, Language.CPP, 0, null, 2, 1);
        insertRun(23, 23, 99, "In Progress...", null, null, Language.CPP, 0, null, 2, 1);
        insertRun(24, 24, 99, "In Progress...", null, null, Language.CPP, 0, null, 2, 1);
        insertRun(30, 30, 99, "In Progress...", null, null, Language.CPP, 0, null, 1, 1);
        insertRun(31, 31, 99, "In Progress...", null, null, Language.CPP, 0, null, 1, 1);
        insertRun(13, 13, 99, "In Progress...", null, 1, Language.CPP, 1000000001, null, 2, 1);
        insertRun(14, 14, 99, "In Progress...", null, 1, Language.CPP, 1000000002, null, 2, 1);
        insertRun(15, 15, 99, "In Progress...", null, 1, Language.CPP, 1000000000, null, 2, 1);
        insertRun(16, 16, 99, "In Progress...", null, null, Language.CPP, 0, null, 3, 2);
        insertRun(17, 17, 99, "In Progress...", null, null, Language.CPP, 0, null, 3, 2);
        insertRun(50, 9, 99, "In Progress...", null, null, Language.CPP, 0, null, 1, 1);
        insertRun(51, 51, 99, "In Progress...", null, null, Language.BINARY, 0, null, 1, 1);
        insertRun(52, 52, 99, "In Progress...", null, null, Language.PYTHON, 0, null, 1, 1);
        insertRun(53, 53, 99, "In Progress...", null, null, Language.CS, 0, null, 1, 1);
        insertRun(54, 54, 99, "In Progress...", null, null, Language.JAVA, 0, null, 2, 2);
        insertRun(55, 55, 99, "In Progress...", null, null, Language.JAVA, 0, null, 2, 2);
        insertRun(56, 56, 99, "In Progress...", null, null, Language.CMAKE, 0, null, 1, 1);
        insertRun(69, 99, 99, "In Progress...", null, null, Language.CPP, 0, null, 3, 1);
        insertRun(70, 50, 99, "In Progress...", null, null, Language.CPP, 0, null, 5, 1);
        
        insertRun(75, 161, 99, "In Progress...", null, null, Language.CPP, 0, null, 6, 1);
        insertRun(72, 12, 99, "In Progress...", null, null, Language.CPP, 0, null, 6, 1);
        insertRun(73, 162, 99, "In Progress...", null, null, Language.CPP, 0, null, 6, 1);
        insertRun(74, 6, 99, "In Progress...", null, null, Language.CPP, 0, null, 6, 1);
        insertRun(71, 4, 99, "In Progress...", null, null, Language.CPP, 0, null, 6, 1);
        
        insertRun(80, 161, 99, "In Progress...", null, null, Language.CPP, 0, null, 7, 1);
        insertRun(77, 12, 99, "In Progress...", null, null, Language.CPP, 0, null, 7, 1);
        insertRun(78, 162, 99, "In Progress...", null, null, Language.CPP, 0, null, 7, 1);
        insertRun(79, 6, 99, "In Progress...", null, null, Language.CPP, 0, null, 7, 1);
        insertRun(76, 4, 99, "In Progress...", null, null, Language.CPP, 0, null, 7, 1);
    }

    private void insertRun(Integer id, Integer sourceFileId, Integer compilationLogFileId, String runStatus,
            RuntimeCode runtimeCodeOfCompilation, Integer globalClockTicks, Language language,
            long submitDate, Double runScore, Integer problemDefinitionId, Integer userId) {
        Run run = new Run();
        OptFile sourceFile = null;
        OptFile compilationLog = null;
        if (sourceFileId != null) {
            sourceFile = optFileService.findById(sourceFileId);
        }
        if (compilationLogFileId != null) {
            compilationLog = optFileService.findById(compilationLogFileId);
        }
        ProblemDefinition problemDefinition = problemDefinitionService.findById(problemDefinitionId);
        User user = userService.findById(userId);

        run.setId(id);
        run.setRunStatusPublic(runStatus);
        run.setRunStatusPrivate(runStatus);
        run.setRuntimeCodeCom(runtimeCodeOfCompilation);
        run.setGlobalClockTicksPublic(globalClockTicks);
        run.setGlobalClockTicksPrivate(globalClockTicks);
        run.setLanguage(language);
        run.setSubmitDate(new Timestamp(submitDate));
        run.setRunScorePublic(runScore);
        run.setRunScorePrivate(runScore);

        run.setSourceFile(sourceFile);
        run.setCompilationLog(compilationLog);
        run.setProblemDefinition(problemDefinition);
        run.setUser(user);

        runService.create(run);
    }

    private void insertOptTests() {
        insertOptTest(2, null, null, 1, 2, 1, 1.0, null, 14, 13, 99, 99);
        insertOptTest(3, null, null, 1, 2, 1, 1.0, null, 3, 13, 99, 99);
        insertOptTest(4, null, null, 1, 2, 1, 1.0, null, 4, 13, 99, 99);
        insertOptTest(5, null, null, 1, 2, 1, 1.0, null, 5, 13, 99, 99);
        insertOptTest(6, null, null, 1, 2, 1, 1.0, null, 6, 13, 99, 99);
        insertOptTest(7, null, null, 1, 2, 1, 1.0, null, 7, 13, 99, 99);
        insertOptTest(8, null, null, 1, 2, 1, 3.0, null, 8, 13, 99, 99);
        insertOptTest(9, null, null, 1, 2, 1, 1.0, null, 9, 13, 99, 99);
        insertOptTest(10, null, null, 1, 2, 1, 1.0, null, 10, 13, 99, 99);
        insertOptTest(11, null, null, 1, 2, 1, 1.0, null, 11, 13, 99, 99);
        insertOptTest(12, null, null, 1, 2, 1, 1.0, null, 12, 13, 99, 99);
        insertOptTest(13, null, null, 1, 2, 1, 1.0, null, 13, 13, 99, 99);
        insertOptTest(14, null, null, 1, 2, 1, 1.0, null, 14, 14, 99, 99);
        insertOptTest(15, null, null, 1, 2, 1, 1.0, null, 3, 14, 99, 99);
        insertOptTest(16, null, null, 1, 2, 1, 1.0, null, 4, 14, 99, 99);
        insertOptTest(17, null, null, 1, 2, 1, 1.0, null, 5, 14, 99, 99);
        insertOptTest(18, null, null, 1, 2, 1, null, null, 6, 14, 99, 99);
        insertOptTest(19, null, null, 1, 2, 1, 1.0, null, 7, 14, 99, 99);
        insertOptTest(20, null, null, 1, 2, 1, 1.0, null, 8, 14, 99, 99);
        insertOptTest(21, null, null, 1, 2, 1, 1.0, null, 9, 14, 99, 99);
        insertOptTest(22, null, null, 1, 2, 1, 1.0, null, 10, 14, 99, 99);
        insertOptTest(23, null, null, 1, 2, 1, 1.0, null, 11, 14, 99, 99);
        insertOptTest(24, null, null, 1, 2, 1, 1.0, null, 12, 14, 99, 99);
        insertOptTest(25, null, null, 1, 2, 1, 1.0, null, 13, 14, 99, 99);
        insertOptTest(26, null, null, 1, 2, 1, 1.0, null, 14, 15, 99, 99);
        insertOptTest(27, null, null, 1, 2, 1, 1.0, null, 3, 15, 99, 99);
        insertOptTest(28, null, null, 1, 2, 1, 1.0, null, 4, 15, 99, 99);
        insertOptTest(29, null, null, 1, 2, 1, 1.0, null, 5, 15, 99, 99);
        insertOptTest(30, null, null, 1, 2, 1, 5.0, null, 6, 15, 99, 99);
        insertOptTest(31, null, null, 1, 2, 1, 3.0, null, 7, 15, 99, 99);
        insertOptTest(32, null, null, 1, 2, 1, 2.0, null, 8, 15, 99, 99);
        insertOptTest(33, null, null, 1, 2, 1, 1.0, null, 9, 15, 99, 99);
        insertOptTest(34, null, null, 1, 2, 1, 1.0, null, 10, 15, 99, 99);
        insertOptTest(35, null, null, 1, 2, 1, 1.0, null, 11, 15, 99, 99);
        insertOptTest(36, null, null, 1, 2, 1, 1.0, null, 12, 15, 99, 99);
        insertOptTest(37, null, null, 1, 2, 1, 1.0, null, 13, 15, 99, 99);
        insertOptTest(50, null, null, 1, 2, 1, 1.0, null, 1, 50, 99, 99);
        insertOptTest(90, null, null, 1, 2, 1, 1.0, null, 2, 69, 99, 99);
        insertOptTest(91, null, null, 1, 2, 1, 1.0, null, 15, 69, 99, 99);
    }

    private void insertOptTest(Integer id, RuntimeCode runCode, RuntimeCode judgeCode, long memsize,
            long clockTicks, long instructions, Double score, String judgeStatus, Integer probleminstanceId,
            Integer runId, Integer outputFileId, Integer errputFileId) {
        OptTest test = new OptTest();

        ProblemInstance instance = problemInstanceService.findById(probleminstanceId);
        Run run = runService.findById(runId);
        OptFile output = null;
        OptFile errput = null;
        if (outputFileId != null) {
            output = optFileService.findById(outputFileId);
        }
        if (errputFileId != null) {
            errput = optFileService.findById(errputFileId);
        }

        test.setId(id);
        test.setRuntimeCodeJud(runCode);
        test.setRuntimeCodeJud(judgeCode);
        test.setMemorySize(memsize);
        test.setClockTicks(clockTicks);
        test.setInstructions(instructions);
        test.setScore(score);
        test.setJudgeStatus(judgeStatus);

        test.setProblemInstance(instance);
        test.setRun(run);
        test.setOutputFile(output);
        test.setErrputFile(errput);

        optTestService.create(test);
    }

}
