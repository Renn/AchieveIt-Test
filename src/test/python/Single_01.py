# This is a performance test script for AchieveIt, a project of Software Development Practice in East China Normal University.
# @Author: Shangzhen Li
from locust import HttpLocust, TaskSet, task, constant
import random

class WebsiteTasks(TaskSet):

    def setup(self):
        pass

    def on_start(self):
        pass

    @task
    def login(self):
        no=random.randint(0,999)
        username = 'test_userid_%d'%no
        password = 'test_password_%d'%no
        with self.client.post("/user/userLoginByID",
            {
                'userID' : username,
                'userPassword' : password,
            },catch_response=True) as response:
            responseJson=response.json()
            if not responseJson['success']:
                response.failure('success is not True')
        

class WebsiteUser(HttpLocust):
    task_set = WebsiteTasks
    wait_time = constant(0)
