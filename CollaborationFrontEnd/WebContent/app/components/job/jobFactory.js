/**
 * 
 */
var job = angular.module('jobModule',[]);

job.factory('jobFactory',['$http','$q',
	
	function($http, $q) {
	
	var jobUrl = 'http://localhost:2222/theBackendProject';
	
	return {
		addJob : addJob,
		joblist : joblist,
		viewJob : viewJob,
		addJobapp : addJobapp,
		jobapplist : jobapplist,
		editJob : editJob,
		deleteAJob : deleteAJob,
		appliedJobs : appliedJobs
        
	};
	
	function addJob(job) {
        var deferred = $q.defer();
        console.log('add job method in factory'+job);
        $http.post(jobUrl + '/job/new', job).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
           
        );
        
        console.log('End of add job mehod in factory');
        return deferred.promise;
    }
	
	// fn to edit job
	function editJob(job) {
        var deferred = $q.defer();
        console.log('editJob method in factory'+job);
        $http.post(jobUrl + '/edit/job', job).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
           
        );
        
        console.log('End of editJob mehod in factory');
        return deferred.promise;
    }
	
	function joblist() {
        console.log('Inside job list factory now');
       var deferred = $q.defer();
       $http.get(jobUrl + '/job/list')
           .then (
               function(response) {
                   deferred.resolve(response.data);
               },
               function(errResponse) {
                   deferred.reject(errResponse);
               }
           );
       console.log('End of job list mehod in factory');
           return deferred.promise;
   }
	
	//Function for viewing single blog using blog id as a parameter
    function viewJob(id) {
        console.log('Inside factory now job single');
        var deferred = $q.defer();

        $http.get(jobUrl + '/job/' + id)
            .then (
                function(response) {
                    deferred.resolve(response.data);
                },
                function(errResponse) {
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
    }
	
    // function to apply for a job
    function addJobapp(jobapp) {
        var deferred = $q.defer();
        console.log('add job application method in factory');
        $http.post(jobUrl + '/jobapp/new', jobapp).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
           
        );
        
        console.log('End of add job application mehod in factory');
        return deferred.promise;
    }
    
    function jobapplist(jobid1) {
        console.log('Inside job app list factory now');
       var deferred = $q.defer();
       $http.get(jobUrl + '/jobapp/list/' + jobid1)
           .then (
               function(response) {
                   deferred.resolve(response.data);
               },
               function(errResponse) {
                   deferred.reject(errResponse);
               }
           );
       console.log('End of job app list mehod in factory');
           return deferred.promise;
   }
    
    // fn to delete a job
    function deleteAJob(jobid) {
        console.log('Inside job delete method factory now');
       var deferred = $q.defer();
       $http.post(jobUrl + '/job/delete/' + jobid)
           .then (
               function(response) {
                   deferred.resolve(response.data);
               },
               function(errResponse) {
                   deferred.reject(errResponse);
               }
           );
       console.log('End of job delete mehod in factory');
           return deferred.promise;
   }
    
    // fn to get list of applied jobs
    function appliedJobs(userid) {
        console.log('Inside applied jobs list factory now');
       var deferred = $q.defer();
       $http.get(jobUrl + '/job/applied/list/' + userid)
           .then (
               function(response) {
                   deferred.resolve(response.data);
               },
               function(errResponse) {
                   deferred.reject(errResponse);
               }
           );
       console.log('End of applied jobs list mehod in factory');
           return deferred.promise;
   }
    
    /******************************/
	}]);