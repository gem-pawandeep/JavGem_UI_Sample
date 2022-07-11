Feature: Example

  Scenario: Login GemEcoSystem
    Given Request : login User using readFile : readfile(src/main/resources/login.json)
    Given set endpoint = https://apis-beta.gemecosystem.com/login
    And Request : login User using Json :
    """
    {
  "method": "POST",
  "endpoint": "#endpoint#",
  "expected_status": 200,
  "request_body": {
    "username":"raghav.suneja",
    "password":"raghav234"
  }
}
    """
    And Request : login user inline json : { "method": "POST", "endpoint": "#endpoint#", "expected_status": 200, "request_body": { "username":"raghav.suneja", "password":"raghav234" } }

  Scenario: Create user GemEcoSystem
    Given Request : Create User using readFile : readfile(src/main/resources/PostGem.json)
    Given set endpoint = https://apis-beta.gemecosystem.com/user
    And Request : Create User using Json :
    """
    {
  "method": "POST",
  "endpoint": "#endpoint#",
  "expected_status": 201,
"request_body":{
  "username":"#epoch#",
  "firstName":"test",
  "lastName":"user",
  "email":"#random-[2,9.5,-4]##random-[2,9.5,-4]#@geminisolutions.com",
  "password":"abcd",
  "company":"Dummy"
}
}
    """
    And Request : login user inline json : {"method": "POST","endpoint": "#endpoint#","expected_status": 201,"request_body":{"username":"#epoch#","firstName":"test","lastName":"user","email":"#random-[2,9.5,-4]##random-[2,9.5,-4]#@geminisolutions.com","password":"abcd","company":"Dummy"}}

  Scenario: Get user GemEcoSystem
    Given Request : Get User using readFile : readfile(src/main/resources/GetGem.json)
    Given set endpoint = https://apis-beta.gemecosystem.com/validate/username?username=user123
    And Request : Create User using Json :
    """
    {
  "method": "GET",
  "endpoint": "#endpoint#",
  "expected_status": 200
  }
    """
    And Request : login user inline json : {"method": "GET","endpoint": "#endpoint#","expected_status": 200}

  Scenario: Get user-2 GemEcoSystem
    Given Request : Get User using readFile : readfile(src/main/resources/GetGem.json)
    Given set endpoint = https://apis-beta.gemecosystem.com/validate/username?username=user1234566
    And Request : Create User using Json :
    """
    {
  "method": "GET",
  "endpoint": "#endpoint#",
  "expected_status": 200
  }
    """
    And Request : login user inline json : {"method": "GET","endpoint": "#endpoint#","expected_status": 200}

  Scenario: Get Company GemEcoSystem
    Given Request : Get User using readFile : readfile(src/main/resources/GetGem.json)
    Given set endpoint = https://apis-beta.gemecosystem.com/company
    And Request : Create User using Json :
    """
    {
  "method": "GET",
  "endpoint": "#endpoint#",
  "expected_status": 200
  }
    """
    And Request : login user inline json : {"method": "GET","endpoint": "#endpoint#","expected_status": 200}

  Scenario: Get Test suite-1 GemEcoSystem
    Given Request : Get User using readFile : readfile(src/main/resources/GetTestGem.json)
    Given set endpoint = https://apis-beta.gemecosystem.com/suiteexe?s_run_id=4d767509-f3b0-4fb6-a59d-85bfc384188b
    And Request : Create User using Json :
    """
    {
  "method": "GET",
  "endpoint": "#endpoint#",
  "expected_status": 200
  }
    """
    And Request : login user inline json : {"method": "GET","endpoint": "#endpoint#","expected_status": 200}

  Scenario: Get Test suite-2 GemEcoSystem
    Given Request : Get User using readFile : readfile(src/main/resources/GetTestGem2.json)
    Given set endpoint = https://apis-beta.gemecosystem.com/suiteexe?s_run_id=pygem_project_beta_42d22827-a51e-4d21-9297-14d1be5cdb7f
    And Request : Create User using Json :
    """
    {
  "method": "GET",
  "endpoint": "#endpoint#",
  "expected_status": 200
  }
    """
    And Request : login user inline json : {"method": "GET","endpoint": "#endpoint#","expected_status": 200}

  Scenario: Get User
    Given Request : Get User using readFile : readfile(src/main/resources/GetUser.json)
    Given set endpoint = https://gorest.co.in/public/v2/users
    And Request : Create User using Json :
    """
  {
  "method": "GET",
  "endpoint": "#endpoint#",
  "expected_status": 200
  }
    """
    And Request : login user inline json : {"method": "GET","endpoint": "#endpoint#","expected_status": 200}

  Scenario: Create user
    Given set endpoint = =https://gorest.co.in/public/v2/users
    Given set name = AKASH
    Given Request : Create User :
     """
    {
    "method": "POST",
    "endpoint": "https://gorest.co.in/public/v2/users",
    "expected_status": 201,
    "request_body": {
      "name": "#name#123",
      "email": "User_#curr-ddMMyyyyhhmmss#@org.com",
      "gender": "Male",
      "status": "active"
    },
    "headers": {
      "Authorization": "Bearer e44ac095d53abb1da69ff4cdc9c0bc24ea741dbc7cbe6f2ba2dfdbec9cb1ecd4"
    }
    }
    """
    And AssertClassCucumber.Assert : sample assertion :
    """
    {
    "response(name)":"equals AKASH123",
    "deepsearch(name)":"contains 123"
    }
    """

  Scenario: Create user using json assertion path
    Given Request : Create User using readFile : readfile(src/main/resources/CreateUser.json)
    And AssertClassCucumber.Assert : sample assertion : readfile(src/main/resources/assertionfile.json)
