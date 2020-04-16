# This is a performance test script for AchieveIt, a project of Software Development Practice in East China Normal University.
# @Author: Shangzhen Li
from locust import HttpLocust, TaskSet, task, constant
import random

class WebsiteTasks(TaskSet):

    activity=['需求开发','设计','编码','单体测试','集成测试','系统测试','交付','范围管理','计划与调整','监控与分析','联络与沟通','外包管理','外包验收','外包支持','配置管理',
    'QA审计','会议报告总结','培训','其他']
    startTimeBase=1586991600000
    endTimeBase=1587020400000

    def setup(self):
        pass

    def on_start(self):
        # Login to meet precondition
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
        projectId='2032-0000-S-11'
        with self.client.get('/project/getProjectByID?projectID=%s'%projectId,catch_response=True) as response:
            responseJson=response.json()
            if responseJson['success']:
                response.success()
                return projectId
            else:
                response.failure('success is not True')
                return 0
    
    @task
    def applyWorkHour(self):
        projectId=self.selectProject()
        featureNo=random.randint(0,20)
        activityNo=random.randint(0,18)
        startTimeOffset=random.randint(0,10800000)
        endTimeOffset=random.randint(0,10800000)
        with self.client.post('/workHour/applyWorkHour',{
            'featureName':'feature_%d'%featureNo,
            'activityName':self.activity[activityNo],
            'startTime':'%s'%(self.startTimeBase+startTimeOffset),
            'endTime':'%s'%(self.endTimeBase+endTimeOffset),
            'projectID':projectId},catch_response=True) as response:
            responseJson=response.json()
            if responseJson['success']:
                response.success()
            else:
                response.failure('success is not True')

class WebsiteUser(HttpLocust):
    task_set = WebsiteTasks
    wait_time = constant(0)
