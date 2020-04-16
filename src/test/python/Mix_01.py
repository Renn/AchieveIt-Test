# This is a performance test script for AchieveIt, a project of Software Development Practice in East China Normal University.
# @Author: Shangzhen Li
from locust import HttpLocust, TaskSet, task, constant
import random

class WebsiteTasks(TaskSet):

    projectIdList=['2032-0000-S-05','2032-0000-S-03','2032-0000-S-06','2032-0000-S-10','2032-0000-S-11','2032-0000-S-00','2032-0000-S-12','2032-0000-S-13',
        '2032-0000-S-15','2032-0000-S-19','2032-0000-S-14','2032-0000-S-16','2032-0000-S-18','2032-0000-S-17','2032-0000-S-07','2032-0000-S-08','2032-0000-S-09',
        '2032-0000-S-20','2032-0000-S-01','2032-0000-S-04']

    def setup(self):
        pass

    def on_start(self):
        pass

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

    def selectProject(self):
        self.client.get('/project/getProjectList?searchCondition=&projectStatus=-1')
        index=random.randint(0,19)
        projectId=self.projectIdList[index]
        with self.client.get('/project/getProjectByID?projectID=%s'%projectId,catch_response=True) as response:
            responseJson=response.json()
            if responseJson['success']:
                response.success()
                return projectId
            else:
                response.failure('success is not True')
                print(responseJson)
                return 0

    @task(70)
    def login_getBasicInfo(self):
        self.login()
        self.selectProject()


    @task(29)
    def login_getMembers(self):
        self.login()
        projectId=self.selectProject()
        with self.client.get('/project/getMembersByID?projectID=%s&memberRole=%d'%(projectId,-1),catch_response=True) as response:
            responseJson=response.json()
            if responseJson['success']:
                response.success()
            else:
                response.failure('success is not True')
                print(responseJson)

    @task(1)
    def login_getGitRepo(self):
        self.login()
        projectId=self.selectProject()
        with self.client.get('/project/getGitRepoByID?projectID=%s'%projectId,catch_response=True) as response:
            responseJson=response.json()
            if responseJson['success']:
                response.success()
            else:
                response.failure('success is not True')
                print(responseJson)

class WebsiteUser(HttpLocust):
    task_set = WebsiteTasks
    wait_time = constant(0)
