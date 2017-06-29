/**
 * 
 */
job.controller('jobController',['jobFactory','$routeParams', '$location', '$route', '$rootScope', '$q',
	function(jobFactory,$routeParams, $location, $route, $rootScope, $q) {
	
	var self = this;
	
	self.job = {
			
			jobid : null,
			jtitle: '',
			jdata: '',
			active : 'Y'
	}
	
	self.jobapp = {
			
			jobappid : null,
			job : '',
			user : '',
			active : 'Y'
	}
	
	self.jobCount = [];
	
	self.appliedJobsCount = [];
	
	// variable to hold the list 
	self.joblist = [];
	
	//function for adding a new blog
    self.addJob = function () {
    	
    	self.job.user = $rootScope.user;
    	self.job.jdate = new Date();
        console.log('in job controller');
         //calling the addBlog method in the factory
        jobFactory.addJob(self.job)
            .then (
                function(job) {
                    self.job =  job;
                    Materialize.toast('New job added successfully', 4000);
                    var bId = self.job.jobid;
                    $location.path('/job/' + bId);
                }, function (errResponse) {
                }
            );
         console.log('end of job controller');
    }
	
    //function for editing job
    self.editJob = function () {

        console.log('in editJob method controller');
        var jobId = self.job.jobid;
        self.job.jdate = new Date();
        jobFactory.editJob(self.job)
            .then (
                function(job1) {
                    self.job =  job1;
                    
                    $location.path('/job/' + jobId);
                }, function (errResponse) {
                }
            );
         console.log('end of controller editJob method');
    }
    
    
    joblist();
    
    function joblist() {
    	
    	console.log('inside job list method');
    	jobFactory.joblist()
           .then (
               function(jobs) {   
                   self.joblist = jobs;
                   self.jobCount = self.joblist.length;
                   console.log(self.joblist);
               },
               function(errResponse) {
                   console.log('job list Failure!');
               }
           );
        console.log('End of job list method');
   }
    
 // for viewing single job
	self.singleJob = {};
    
    //function for viewing single job
    self.viewJob = function() {
    	
    	jobapplist().then(function(jobapps1){
            self.jobApplicants = jobapps1; //store list of participated users in already defined array
            for(var jobappid in self.jobApplicants) {
                if(user.userid == self.jobApplicants[jobappid].user.userid) { 
                    self.hasApplied = true;  /*If active user is present in the list of participant set the flag as true & store its fetch its request status*/
                                        
                    break;                     
                }
            }
    	
        //Assigning blog id to variable blogId
        var blogId = $routeParams.id;
        jobFactory.viewJob(blogId)
            .then (
                function(job) {
                    self.singleJob = job;
                    self.job = self.singleJob;
                    // calling job applicants list method
                    jobapplist();
                },
                function(errResponse) {
                }
            );
        
    	});

    } 
    
  //function for adding a new job application
    self.addJobapp = function (selectedJob) {
    	
    	self.jobapp.user = $rootScope.user;
    	self.jobapp.job = selectedJob;
    	self.jobapp.appdate = new Date();
    	
        console.log('in job app controller');
         //calling the addBlog method in the factory
        jobFactory.addJobapp(self.jobapp)
            .then (
                function(resp) {
                    $route.reload();
                }, function (errResponse) {
                	
                }
            );
         console.log('end of job app controller');
    }
    
    
    
    // fn to get job applicants list by jobid
    function jobapplist() {
    	var deferred = $q.defer();
    	var jobid1 = $routeParams.id;
    	console.log('inside job app list method');
    	jobFactory.jobapplist(jobid1)
           .then (
               function(jobapps) {   
                   self.jobapplist = jobapps;
                   deferred.resolve(jobapps);
                   console.log(self.jobapplist);
               },
               function(errResponse) {
                   console.log('job list Failure!');
               }
           );
    	return deferred.promise;
        console.log('End of job app list method');
   }
    
    // fn to delete a job
    self.deleteAJob = function() {
    	
    	var jobid1 = $routeParams.id;
    	console.log('inside job delete method');
    	jobFactory.deleteAJob(jobid1)
           .then (
               function(job) {   
            	 
            	   $location.path('/job/list');
               },
               function(errResponse) {
                   console.log('job delete Failure!');
               }
           );
    	
        console.log('End of job delete method');
   }
    
    // call to appliedJobs method
    appliedJobs();
    
    // fn to get list of applied jobs
    function appliedJobs() {
    	var userid1 = $rootScope.user.userid;
    	console.log('inside appliedJobs list method');
    	jobFactory.appliedJobs(userid1)
           .then (
               function(appliedjobs) {   
                   self.appliedJobsList = appliedjobs;
                   self.appliedJobsCount = self.appliedJobsList.length;
                   console.log(self.appliedJobsList);
               },
               function(errResponse) {
                   console.log('appliedJobs Failure!');
               }
           );
        console.log('End of appliedJobs list method');
   }
    
    /**************************/
}]);