/**
 * 
 */
user.controller('userController',['userFactory', 'loginFactory', 'friendFactory','$routeParams', '$location', '$route', '$rootScope', '$scope', '$timeout',
	function(userFactory, loginFactory, friendFactory, $routeParams, $location, $route, $rootScope, $scope, $timeout) {
	
	var self = this;
	
	
	
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
	
	self.user = loginFactory.loadUserFromCookie();
	
	self.friend = {
			friendid : null,
			userid1 : '',
			userid2 : '',
			active : 'Y'
	}
	
	self.onlineFriendsCount = [];
	
	self.otherPeopleCount = [];
	
	//function to add a new friend
    self.addFriend = function(userid2) {
    	
    	self.friend.userid1 = $rootScope.user.userid;
    	
    	
    	self.friend.userid2 = userid2;
        console.log('in friend controller');
         //calling the addBlog method in the factory
        friendFactory.addFriend(self.friend)
            .then (
                function(resp) {
                	Materialize.toast('Friend request sent!', 4000);
                    $route.reload();
                }, function (errResponse) {
                }
            );
         console.log('end of friend controller');
    }
	
	// calling not friends list function
    notMyFriendlist();
    
    // Not friends list function
    function notMyFriendlist() {
    	var userid = $rootScope.user.userid;
    	console.log('inside not friend list method');
    	friendFactory.notMyFriendlist(userid)
           .then (
               function(notfriends) {   
                   self.notMyFriendlist = notfriends;
                   self.otherPeopleCount = self.notMyFriendlist.length;
                   console.log(self.notMyFriendlist);
               },
               function(errResponse) {
                   console.log('not friends list Failure!');
               }
           );
        console.log('End of not friend list method');
   }
	
	
	
	
	//function for adding a new user
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
    
    //function for editing user profile
    self.editUser = function () {

        console.log('in user editUser method controller');
        var userId = $rootScope.user.userid;
        userFactory.editUser(self.user)
            .then (
                function(user1) {
                    self.user =  user1;
                    loginFactory.saveUser(user1);
                    
                    $location.path('/profile/' + userId);
                }, function (errResponse) {
                }
            );
         console.log('end of user controller editUser method');
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
    
    //Function to view list of all users
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
    
  //Function to disapprove a user
    self.userDisappr = function(id) {      
    	userFactory.userDisappr(id)
            .then (
                function(user) {
                    $route.reload();
                },
                function(errResponse) {
                }
            );
    }
    
    // Function to delete a user req permanently
    self.userDelete = function(id) {      
    	userFactory.userDelete(id)
            .then (
                function(user) {
                    $route.reload();
                },
                function(errResponse) {
                }
            );
    }
    
    //Function to change role to USER
    self.changeRoleUser = function(id) {      
    	userFactory.changeRoleUser(id)
            .then (
                function(user) {
                    $route.reload();
                },
                function(errResponse) {
                }
            );
    }
    
  //Function to change role to ADMIN
    self.changeRoleAdmin = function(id) {      
    	userFactory.changeRoleAdmin(id)
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
    
    // calling rejectedUsersList function
    rejectedUsersList();
    
    // fn to display rejectedUsersList
    function rejectedUsersList() {
    	userFactory.rejectedUsersList()
            .then (
                function(users) {   
                    self.rejectedUsersList = users;
                   
                    console.log(self.rejectedUsersList);
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
    
    
    
    // calling onlineFriendsList method
    onlineFriendsList();
    
    //Function to get list of online friends
    function onlineFriendsList() {
    	var myuserid = $rootScope.user.userid;
    	
    	userFactory.onlineFriendsList(myuserid)
            .then (
                function(onlineFrnds) {   
                    self.onlineFriendsList = onlineFrnds;
                    self.onlineFriendsCount = self.onlineFriendsList.length;
                    console.log(self.onlineFriendsList);
                },
                function(errResponse) {
                    console.log('Failure!');
                }
            );
    }
    
    /**************************/
}]);