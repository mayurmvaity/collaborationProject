/**
 * 
 */
window.routes = {
		
		//Form for creating new user
	    "/home": {
	        templateUrl : 'app/components/authpages/login.html',
	        controller : 'loginController',
	        controllerAs : 'loginCtrl',
	        requireLogin: false,
	        roles: ['GUEST']
	       
	    },
	    
	  //logging out
	    "/logout": {
	        templateUrl : 'app/components/authpages/login.html',
	        controller : 'loginController',
	        controllerAs : 'loginCtrl',
	        requireLogin: false,
	        roles: ['GUEST']
	       
	    },
	    
	  //Form for creating new user
	    "/profile/:userId": {
	        templateUrl : 'app/components/userstuff/profile.html',
	        controller : 'userController',
	        controllerAs : 'userCtrl',
	        requireLogin: true,
	        roles: ['User', 'Admin']
	       
	    },
		
		
		//Form for creating new user
	    "/user/new": {
	        templateUrl : 'app/components/userstuff/register.html',
	        controller : 'userController',
	        controllerAs : 'userCtrl',
	        requireLogin: true,
	        roles: ['User']
	       
	    },
	    
	  //For viewing list of users
	     "/user/list": {
	    	 	templateUrl : 'app/components/userstuff/userList.html',
		        controller : 'userController',
		        controllerAs : 'userCtrl',
		        requireLogin: true,
		        roles: ['Admin']
	       
	    },

	  //For viewing list of users
	     "/user/nalist": {
	    	 	templateUrl : 'app/components/userstuff/usernaList.html',
		        controller : 'userController',
		        controllerAs : 'userCtrl',
		        requireLogin: true,
		        roles: ['Admin']
	       
	    },

		//Form for creating new blog
	    "/blog/new": {
	        templateUrl : 'app/components/blog/newBlog.html',
	        controller : 'blogController',
	        controllerAs : 'blogCtrl',
	        requireLogin: true,
	        roles: ['User', 'Admin']
	       
	    },
	    
	    // add new forum page
	    "/forum/new": {
	        templateUrl : 'app/components/forum/newforum.html',
	        controller : 'forumController',
	        controllerAs : 'forumCtrl',
	        requireLogin: true,
	        roles: ['Admin']
	       
	    },
	    
	    // forum list page
	    "/forum/list": {
	        templateUrl : 'app/components/forum/forumlist.html',
	        controller : 'forumController',
	        controllerAs : 'forumCtrl',
	        requireLogin: true,
	        roles: ['User', 'Admin']
	       
	    },
	    
	    // single forum page
	    "/forum/:id": {
	        templateUrl : 'app/components/forum/singleforum.html',
	        controller : 'forumController',
	        controllerAs : 'forumCtrl',
	        requireLogin: true,
	        roles: ['User', 'Admin']
	       
	    },
	    
	 // add new forum page
	    "/job/new": {
	        templateUrl : 'app/components/job/newJob.html',
	        controller : 'jobController',
	        controllerAs : 'jobCtrl',
	        requireLogin: true,
	        roles: ['Admin']
	       
	    },

	  //For viewing list of blogs
	     "/job/list": {
	        templateUrl : 'app/components/job/jobList.html',
	        controller : 'jobController',
	        controllerAs : 'jobCtrl',
	        requireLogin: true,
	        roles: ['User', 'Admin']
	       
	    },
	    
	    //For viewing single blog
	    "/job/:id": {
	        templateUrl : 'app/components/job/singleJob.html',
	        controller : 'jobController',
	        controllerAs : 'jobCtrl',
	        requireLogin: true,
	        roles: ['User',  'Admin']
	        
	    },
	    
	  //For viewing list of blogs
	     "/blog/list": {
	        templateUrl : 'app/components/blog/bloglist.html',
	        controller : 'blogController',
	        controllerAs : 'blogCtrl',
	        requireLogin: true,
	        roles: ['User', 'Admin']
	       
	    },
	    
	    //For viewing list of unapproved blogs
	     "/blog/nalist": {
	        templateUrl : 'app/components/blog/nabloglist.html',
	        controller : 'blogController',
	        controllerAs : 'blogCtrl',
	        requireLogin: true,
	        roles: ['Admin']
	       
	    },
	    
	    //For viewing single blog
	    "/blog/:id": {
	        templateUrl : 'app/components/blog/singleBlog.html',
	        controller : 'blogController',
	        controllerAs : 'blogCtrl',
	        requireLogin: true,
	        roles: ['User', 'Admin']
	        
	    },
	    
	  //Form for creating new evt
	    "/adminevent/new": {
	        templateUrl : 'app/components/adminevent/newEvent.html',
	        controller : 'admineventController',
	        controllerAs : 'evtCtrl',
	        requireLogin: true,
	        roles: ['Admin']
	       
	    },
	    
	  //For viewing list of events
	     "/adminevent/list": {
	        templateUrl : 'app/components/adminevent/eventList.html',
	        controller : 'admineventController',
	        controllerAs : 'evtCtrl',
	        requireLogin: true,
	        roles: ['User', 'Admin']
	       
	    },
	    
	    //For viewing single event
	    "/adminevent/:id": {
	        templateUrl : 'app/components/adminevent/singleEvent.html',
	        controller : 'admineventController',
	        controllerAs : 'evtCtrl',
	        requireLogin: true,
	        roles: ['User', 'Admin']
	        
	    },
	    
	  //For navigating to error page
	    "/error": {
	        templateUrl : 'app/components/authpages/error.html',
	        controller : 'loginController',
	        controllerAs : 'loginCtrl',
	        requireLogin: false,
	        roles: ['GUEST']
	    }

};

lifeinvader.constant('url', 'http://localhost:2222/theBackendProject');

lifeinvader.config(['$routeProvider', '$httpProvider',  function($routeProvider, $httpProvider){

    

    //for page navigartion through url
    for(var path in window.routes) {
        $routeProvider.when(path, window.routes[path]); 
    }
    
    $routeProvider.otherwise({redirectTo: '/home'});

     
}]);

lifeinvader.run(function ($rootScope, $location, loginFactory, $log) {

    $rootScope.$on('$locationChangeStart', function (event, next, current) {
    	$log.log("NEXT = "+ next);
        if(next == current) {
                $rootScope.user = loginFactory.loadUserFromCookie();
                $rootScope.authenticated = loginFactory.getUserIsAuthenticated();
                return;            
        }                   
        // For interating through all the routes
        for (var i in window.routes) {    
        	
        	
            if (next.indexOf(i) != -1 || (i.indexOf("/:id") != -1 )) {
                $rootScope.user = loginFactory.loadUserFromCookie();
                $rootScope.authenticated = loginFactory.getUserIsAuthenticated();

               // console.log(loginFactory.getUserIsAuthenticated());
                //if trying to access page that requires login and user is not authenticated redirect to login page
                
                if (window.routes[i].requireLogin && !loginFactory.getUserIsAuthenticated()) {
                    $location.path('/home');
                } 
                else if((loginFactory.getUserIsAuthenticated()) && (window.routes[i].roles.indexOf(loginFactory.getRole())==-1)) {
	                		$log.log("1st " + loginFactory.getUserIsAuthenticated());
	                		$log.log("roles inside " + (window.routes[i].roles));
	                		$log.log("Role ="+ loginFactory.getRole());
	                		$log.log("route ="+ window.routes[i]);
	                		$log.log("route2 ="+ i);
	                        $location.path('/error');
                        }
                        
            }
        }
        
    });

    $rootScope.logout = function() {
    //calling the log out function created in the AuthenticationFactory
    	loginFactory.logout($rootScope.user).then(
        function() {
            
        	loginFactory.setUserIsAuthenticated(false);
            $rootScope.authenticated = false;
            $rootScope.message = "Logout successfully!";
            $location.path('/logout');
        }
    );
};

});