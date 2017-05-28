/**
 * 
 */
job.controller('jobController',['jobFactory','$routeParams', '$location', '$route', '$rootScope',
	function(jobFactory,$routeParams, $location, $route, $rootScope) {
	
	var self = this;
	
	self.job = {
			
			jobid : null,
			jtitle: '',
			jdata: '',
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
        //Assigning blog id to variable blogId
        var blogId = $routeParams.id;
        jobFactory.viewJob(blogId)
            .then (
                function(job) {
                    self.singleJob = job;
                   
                },
                function(errResponse) {
                }
            );

    } 
    
}]);