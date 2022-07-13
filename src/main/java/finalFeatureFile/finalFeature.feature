Feature: Example

  Scenario: Using Get method we are hitting the APIs
    Given test_name Test Case to get all users
    Given endpoint https://reqres.in/api/users
    Given expected_status 200
    Given parameters page=2
    Given parameters hello=3
    Given parameters style=3
    Given parameters popo=5
    Given METHOD GET
#    Given Assert : TEST3 : response(first_name) in [AKASH123,Michael]
    Given Assert : TEST1 : response(page) contains 2
    Given Assert : TEST2 : response(total_pages) Equals 2
    Given Assert : TEST3 : response(total) in [AKASH123,Michael,morpheus,12]

  Scenario: Using Given method
    Given test_name hello from day 1
    Given endpoint https://gorest.co.in/public/v2/users
    Given expected_status 201
    Given request_body {"name": "1.0raghav suneja","email": "raghav@gmail.com","gender": "male","status": "active"}
    Given headers {"Authorization": "Bearer e44ac095d53abb1da69ff4cdc9c0bc24ea741dbc7cbe6f2ba2dfdbec9cb1ecd4"}
    Given METHOD POST

  Scenario: Using Post  method Generate token
    Given test_name Generate a token
    Given endpoint https://apis-beta.gemecosystem.com/bridge/token
    Given expected_status 200
    Given headers {"Authorization": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyYWdoYXYuc3VuZWphIiwiaWF0IjoxNjU4MzA5NjcxLCJleHAiOjE2NTgzMDk5NzF9.KAJK1T_YxSc-zmO6eFAuc7tcrJqA0kQWEfZsgRq-tuXd7scqKZoaicHhvbgYQBkUfI7EWQlk4jsikkrIsLYFrQ"}
    Given METHOD GET

  Scenario: Using Get method we are hitting the APIs
    Given test_name Test Case to get all users
    Given endpoint https://gorest.co.in/public/v2/users
    Given expected_status 200

    Given METHOD GET

  Scenario: Using Get method we are hitting the APIs to get some data
    Given test_name Test Case to get all users by using GET method
    Given endpoint https://reqres.in/api/unknown/2
    Given expected_status 200

    Given METHOD GET
    Given Assert : TEST1 : response(data)(name) contains rose
    Given Assert : TEST2 : response(data)(id) Equals 2
    Given Assert : TEST3 : response(data)(year) in [AKASH123,Michael,2001]

  Scenario: Using Post  method
    Given test_name Post a record in the database
    Given endpoint https://reqres.in/api/users
    Given expected_status 201
    Given request_body {"name": "morpheus","job": "leader"}
    Given METHOD POST
    Given Assert : TEST1 : response(name) contains morpheus
    Given Assert : TEST2 : response(name) Equals morpheus
    Given Assert : TEST3 : response(job) in [AKASH123,Michael,leader]

  Scenario: Using Patch  method
    Given test_name Update a record in the database
    Given endpoint https://reqres.in/api/users/2
    Given expected_status 201
    Given request_body {"name": "morpheus","job": "leader -updated one"}
    Given METHOD PATCH
    Given Assert : TEST1 : response(job) contains leader
    Given Assert : TEST3 : response(name) equals morpheus
    Given Assert : TEST3 : response(name) in [AKASH123,Michael,morpheus,12]

  Scenario: Using Post  method but unsuccessful login
    Given test_name Login into the database
    Given endpoint https://reqres.in/api/login
    Given expected_status 400
    Given request_body {"email": "peter@klaven"}
    Given METHOD POST

  Scenario: Using Post  method
    Given test_name Post Gemecosystem APIs
    Given endpoint https://apis-beta.gemecosystem.com/testcase
    Given expected_status 201
    Given request_body {"tc_run_id": "dummy_tqest_wid","start_time": 1648730506766.7388,"end_time": 1648730506767.14,"name": "sample_testcase2","category": "sample","status": "PASS","user": "test_user","machine": "csgeek","result_file": null,"product_type": "PYGEM","ignore": false,"miscData": [],"steps":[{"title":"test","desc":"testdesc","status":"PASS"},{"title":"test1","desc":"testdesc1","status":"FAIL"},{"title":"test2","desc":"testdesc2","status":"EXE"}],"s_run_id": "testsuiteioood"}
    Given headers {"bridgeToken":"4bf9946e-9711-4926-9b7c-bd19215e185c1658232106569","username":"raghav.suneja"}
    Given METHOD POST


