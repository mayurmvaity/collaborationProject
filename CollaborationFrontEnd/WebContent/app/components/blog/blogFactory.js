/**
 * 
 */

var blog = angular.module('blogModule',[]);

blog.factory('blogFactory',['$http','$q',
	
	function($http, $q) {
	
	var blogUrl = 'http://localhost:2222/theBackendProject';
	
	return {
        addBlog : addBlog,
        bloglist : bloglist,
        viewBlog : viewBlog,
        likeblog : likeblog,
        blognalist : blognalist,
        blogAppr : blogAppr
        
	};
	
	function addBlog(blog) {
        var deferred = $q.defer();
        console.log('add blog method in factory'+blog);
        $http.post(blogUrl + '/blog/new', blog).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
           
        );
        
        console.log('End of add blog mehod in factory');
        return deferred.promise;
    }
	
	function bloglist() {
        console.log('Inside blog list factory now');
       var deferred = $q.defer();
       $http.get(blogUrl + '/blog/list')
           .then (
               function(response) {
                   deferred.resolve(response.data);
               },
               function(errResponse) {
                   deferred.reject(errResponse);
               }
           );
       console.log('End of blog list mehod in factory');
           return deferred.promise;
   }

	function likeblog(id) {
        var deferred = $q.defer();
        console.log('like method start');
        $http.post(blogUrl + '/blog/update/' + id).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
        );
        console.log('like method end');
        return deferred.promise;
    }
	
	//Function for viewing single blog using blog id as a parameter
    function viewBlog(id) {
        console.log('Inside factory now');
        var deferred = $q.defer();

        $http.get(blogUrl + '/blog/' + id)
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
    
  //Function to fetch blog not approved list
    function blognalist() {
         console.log('Inside factory now');
        var deferred = $q.defer();

        $http.get(blogUrl + '/blog/nalist')
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
    
    // function to approve blog
    function blogAppr(id) {
        var deferred = $q.defer();
        console.log('Approve blog method start...');
        $http.post(blogUrl + '/blog/approv/' + id).then (

            function(response) {
                deferred.resolve(response.data);
            }, 
            function (errResponse) {
                deferred.reject(errResponse);
            }
        );
        console.log('Approve blog method end');
        return deferred.promise;
    }
    
    
	}]);