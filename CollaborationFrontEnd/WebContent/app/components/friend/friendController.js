/**
 * 
 */
friend.controller('friendController', ['friendFactory', '$routeParams', '$location', '$route', '$rootScope', 
	function(friendFactory, $routeParams, $location, $route, $rootScope){
	
	var self = this;
	
	self.friend = {
			friendid : null,
			user1 : '',
			user2 : '',
			active : 'Y'
	}
	
	// list of my friends
	//self.myFriendlist = [];
	
	
	
    // calling frnds req list function
    myFriendlist();
    
    // frnds req list function
    function myFriendlist() {
    	var userid = $rootScope.user.userid;
    	console.log('inside frnds req list method');
    	friendFactory.myFriendlist(userid)
           .then (
               function(friends) {   
                   self.myFriendlist = friends;
                   console.log(self.myFriendlist);
               },
               function(errResponse) {
                   console.log('frnds req list Failure!');
               }
           );
        console.log('End of frnds req list method');
   }
    
    // calling frnd req rcvd list function
    frreqRcvdlist();
    
    // frnd req rcvd list function
    function frreqRcvdlist() {
    	var userid = $rootScope.user.userid;
    	console.log('inside frnd req rcvd list method');
    	friendFactory.frreqRcvdlist(userid)
           .then (
               function(friends) {   
                   self.frreqRcvdlist = friends;
                   console.log(self.frreqRcvdlist);
               },
               function(errResponse) {
                   console.log('frnd req rcvd list Failure!');
               }
           );
        console.log('End of frnd req rcvd list method');
   }
    
    // calling my frnds list function
    myAccFriendlist();
    
    // my friends list function
    function myAccFriendlist() {
    	var userid = $rootScope.user.userid;
    	console.log('inside my friend list method');
    	friendFactory.myAccFriendlist(userid)
           .then (
               function(friends) {   
                   self.myAccFriendlist = friends;
                   console.log(self.myAccFriendlist);
               },
               function(errResponse) {
                   console.log('my friends list Failure!');
               }
           );
        console.log('End of my friends list method');
   }
    
    //Function to accept a request
    self.acceptReq = function(userid1) {   
    	var userid2 = $rootScope.user.userid;
    	friendFactory.acceptReq(userid1, userid2)
            .then (
                function(frnd) {
                    $route.reload();
                },
                function(errResponse) {
                }
            );
    }
    
  //Function to reject a request
    self.rejectReq = function(userid1) {   
    	var userid2 = $rootScope.user.userid;
    	friendFactory.rejectReq(userid1, userid2)
            .then (
                function(frnd) {
                    $route.reload();
                },
                function(errResponse) {
                }
            );
    }
    
    /*********************/

}]);

