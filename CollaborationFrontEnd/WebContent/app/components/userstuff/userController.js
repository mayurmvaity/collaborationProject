/**
 * 
 */
user.controller('userController',['userFactory', 'loginFactory','$routeParams', '$location', '$route', '$rootScope', '$scope', '$timeout',
	function(userFactory, loginFactory, $routeParams, $location, $route, $rootScope, $scope, $timeout) {
	
	var self = this;
	
	self.user1 = loginFactory.loadUserFromCookie();
	
	self.user = {
			
			userid : null,
			fname : '',
			lname : '',
			email : '',
			pw : '',
			pno : '',
			add1 : '',
			add2 : '',
			add3 : '',
			city : '',
			state : '',
			pincode : '',
			role: 'User',
			active : 'Y'
	}
	
	
	
	//function for adding a new blog
    self.addUser = function () {

        console.log('in user controller');
         //calling the addBlog method in the factory
        userFactory.addUser(self.user)
            .then (
                function(user) {
                    self.user =  user;
                    var bId = self.user.userid;
                    $location.path('/home');
                }, function (errResponse) {
                }
            );
         console.log('end of user controller');
    }
	
	//function to fetch user and user detail
    self.fetchUser = function() {
       
        var id = $routeParams.userId;
         userFactory.fetchUser(id)
               .then (
                   function(user1) {
                      // debugger;
                       self.user1 = user1;
                                              
                   },
                   function(errResponse) {
                       
                   }
               );
    }
	
    userlist();
    
    //Function to view list of all jobs
    function userlist() {
    	userFactory.userlist()
            .then (
                function(users) {   
                    self.userlist = users;
                   
                    console.log(self.userlist);
                },
                function(errResponse) {
                    console.log('Failure!');
                }
            );
    }
    
    // calling usernalist function
    usernalist();
    
    //Function to view list of all unapproved users
    function usernalist() {
    	userFactory.usernalist()
            .then (
                function(users) {   
                    self.usernalist = users;
                   
                    console.log(self.userlist);
                },
                function(errResponse) {
                    console.log('Failure!');
                }
            );
    }
    
  //Function to approve a user
    self.userAppr = function(id) {      
    	userFactory.userAppr(id)
            .then (
                function(user) {
                    $route.reload();
                },
                function(errResponse) {
                }
            );
    }
    
    // calling userapprvlist function
    userapprvlist();
    
    //Function to view list of all approved users
    function userapprvlist() {
    	userFactory.userapprvlist()
            .then (
                function(users) {   
                    self.userapprvlist = users;
                   
                    console.log(self.userapprvlist);
                },
                function(errResponse) {
                    console.log('Failure!');
                }
            );
    }
    
  //function for viewing single user
    self.viewUser = function() {
        //Assigning blog id to variable blogId
        var bId = $routeParams.userId;
        console.log('mmmmmmmmmmm'+bId);
        userFactory.viewUser(bId)
            .then (
                function(user) {
                    self.singleUser = user;
                    
                    
                },
                function(errResponse) {
                }
            );

    }
  
    self.picture = undefined;

    // the decached technique is used to see the updated image immediately with out page refresh
    self.user.profile = self.user.profile + '?decached=' + Math.random(); 

    // once the controller loads call the jQuery
    /*$timeout(function () {
        setting();
    }, 100);*/
    
    
    // to upload the file    
    self.uploadFile = function () {
        
        if(self.picture == undefined) {
            return;
        }    
    	// me.picture will get the value from the directive created previously
    	// it is bind to the HTML input  
        userFactory.uploadFile(self.picture)
        .then(
            function(response){
                $rootScope.message = 'Profile picture updated successfully!';
                //message contains the pictureId updated in the backend too
                self.user.profile = response.message + '?decached=' + Math.random();
                // update the controller user too
                $rootScope.user.profile = response.message + '?decached=' + Math.random();
                // need to update the cookie value too
                loginFactory.saveUser($rootScope.user);

                // hide the card panel by setting the rootScope.message as undefined
                $timeout(function() {                    
                    $rootScope.message = undefined;
                },2000);

            },
            function(error){
                console.log(error);
            } 
        )
    };
    
    /**************************/
}]);