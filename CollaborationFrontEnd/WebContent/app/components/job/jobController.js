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
                    var bId = self.job.jobid;
                    $location.path('/job/' + bId);
                }, function (errResponse) {
                }
            );
         console.log('end of job controller');
    }
	
    joblist();
    
    function joblist() {
    	
    	console.log('inside job list method');
    	jobFactory.joblist()
           .then (
               function(jobs) {   
                   self.joblist = jobs;
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
    
    
    /**************************/
}]);