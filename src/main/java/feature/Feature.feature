Feature: Example

  Scenario: TESTCASE-1
    Given Request : Create User :
    """
    {
    "method": "POST",
    "endpoint": "https://gorest.co.in/public/v2/users",
    "expected_status": 201,
    "request_body": {
      "name": "Raghav",
      "email": "User_#curr-ddMMyyyyhhmmss#@org.com",
      "gender": "Male",
      "status": "active"
    },
    "headers": {
      "Authorization": "Bearer e44ac095d53abb1da69ff4cdc9c0bc24ea741dbc7cbe6f2ba2dfdbec9cb1ecd4"
    }
    }
    """
    And Request : Get all user :
     """
    {
    "method": "Get",
    "endpoint": "https://gorest.co.in/public/v2/users",
    "expected_status": 200
    }
    """
    Given Request : Get All user using pipe :
      | endpoint                             | method | expected_status |
      | https://gorest.co.in/public/v2/users | GET    | 200             |

  Scenario: TESTCASE-2
    Given Request : GET Method :
    """
   {
    "test_name": "get random users",
    "method": "Get",
    "endpoint": "https://gorest.co.in/public/v2/users",
    "expected_status": 200,
    "parameters": {
      "gender": "male",
      "status": "inactive"
    }
    }
    """
    And Request : Create user :
     """
   {
    "method": "POST",
    "endpoint": "https://reqres.in/api/users",
    "expected_status": 201,
    "request_body": {
      "name": "kim.jon.un",
      "job": "sde"
    },
    "headers": {
      "Authorization": "Bearer e44ac095d53abb1da69ff4cdc9c0bc24ea741dbc7cbe6f2ba2dfdbec9cb1ecd4"
    }
  }
    """
    Given Request : Test Case to get all users :
      | endpoint                            | method | expected_status |
      | https://reqres.in/api/users?delay=3 | GET    | 200             |

  Scenario: TESTCASE-3
    Given Request : Register user :
    """
  {
    "method": "POST",
    "endpoint": "https://reqres.in/api/register",
    "expected_status": 200,
    "request_body": {
      "email": "eve.holt@reqres.in",
      "password": "pistol"
    },
    "headers": {
      "Authorization": "Bearer e44ac095d53abb1da69ff4cdc9c0bc24ea741dbc7cbe6f2ba2dfdbec9cb1ecd4"
    }
  }
    """
    And Request : Create user :
      | endpoint                            | method | expected_status |
      | https://reqres.in/api/users?delay=3 | GET    | 200             |
    Given Request : Test Case to get all weather forecast :
      """
      {
    "method": "Get",
    "endpoint": "http://www.7timer.info/bin/api.pl",
    "expected_status": 200,
    "parameters": {
      "lon": "113.17",
      "lat": "23.09",
      "product": "astro",
      "output": "json"
    }
  }
      """

  Scenario: TESTCASE-4
    Given Request : Test Case to get all weather forecast :
      | endpoint                        | method | expected_status |
      | https://api.agify.io?name=bella | GET    | 200             |

    And Request : Test Case to get all entries :
      | endpoint                           | method | expected_status |
      | https://api.publicapis.org/entries | GET    | 200             |

    Given Request : Create S_run_id :
    """
    {
    "method": "POST",
    "endpoint": "http://ec2-3-108-218-108.ap-south-1.compute.amazonaws.com:8080/suiteexe",
    "expected_status": 201,
    "request_body":
     {
      "s_run_id": "PYGEM_PROJECT_BETA_#curr-ddMMyyyyhhmm#",
      "s_start_time": 1648730506509.737,
      "s_end_time": 1648730506767.352,
      " status": "FAIL",
      "project_name": "pygem_project",
      "run_type": "ON DEMAND",
      "s_report_type": "API-AUTOMATION",
      "user": "pawandeep",
      "env": "beta",
      "machine": "pa.deep",
      "initiated_by": "PAWAN",
      "run_mode": "windows",
      "miscData": [],
      "s_id": "test_id"
    }
  }
 """

  Scenario: TESTCASE-5
    Given Request : Test Case to get all weather forecast :
      | endpoint                        | method | expected_status |
      | https://api.agify.io?name=bella | GET    | 200             |

    And Request : Test Case to get all entries :
      | endpoint                           | method | expected_status |
      | https://api.publicapis.org/entries | GET    | 200             |

