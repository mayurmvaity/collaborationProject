/**
 * 
 */
forum.controller('forumController',['forumFactory', 'fpostFactory', '$routeParams', '$location', '$route', '$rootScope', '$scope','$q',
	function(forumFactory, fpostFactory, $routeParams, $location, $route, $rootScope, $scope, $q) {
	
	var self = this;
	
	self.forum = {
			
			forumid : null,
			ftitle: '',
			fdata: '',
			active : 'Y'
	}
	
	self.fmember = {
			
			fmemberid : null,
			active : 'Y',
			isApproved : 'N'
	}
	
	self.fpost = {
			fpostid : null,
			fpdata : '',
			active : 'Y'
	}
	
	//function for adding a new blog
    self.addForum = function () {

		self.forum.user = $rootScope.user;
		self.forum.fdate = new Date();
		
        console.log('in forum controller');
         //calling the addBlog method in the factory
        forumFactory.addForum(self.forum)
            .then (
                function(forum) {
                    self.forum =  forum;
                    var bId = self.forum.forumid;
                    $location.path('/forum/' + bId);
                }, function (errResponse) {
                }
            );
         console.log('end of user controller');
    }
	
	// calling method 
	forumlist();
    
	// forum list method
    function forumlist() {
    	
    	console.log('inside forum list method');
    	forumFactory.forumlist()
           .then (
               function(forums) {   
                   self.forumlist = forums;
                   console.log(self.forumlist);
               },
               function(errResponse) {
                   console.log('forum list Failure!');
               }
           );
        console.log('End of forum list method');
   }
	
    // for viewing single forum
	self.singleForum = {};
    
    //function for viewing single job
    self.viewForum = function() {
       /* //Assigning blog id to variable blogId
        var blogId = $routeParams.id;
        
        forumFactory.viewForum(blogId)
            .then (
                function(forum) {
                    self.singleForum = forum;
                    $scope.vforum = forum;
                },
                function(errResponse) {
                }
            );*/
    	
    	getParticipatedUsers().then(
                 function(participatedUsers){
                     self.participatedUsers = participatedUsers; //store list of participated users in already defined array
                     for(var fmemberid in self.participatedUsers) {
                         if(user.userid == self.participatedUsers[fmemberid].user.userid) { 
                             self.isParticipant = true;  /*If active user is present in the list of participant set the flag as true & store its fetch its request status*/
                             self.participantStatus = self.participatedUsers[fmemberid].isApproved;                       
                             break;                     
                         }
                     }
                     if(self.participantStatus == 'Y') {    //if user is participant
                                  self.isApproved = true;
                     }
                     //fetching single forum page here
                     //Assigning forum id to variable forumId
                     var forumId = $routeParams.id;
                     forumFactory.viewForum(forumId)
                     .then (
                         function(forum) {
                             self.singleForum = forum;
                             $scope.vforum = forum;
                             // calling fpost list method 
                             fpostsByForumid();
                         },
                         function(errResponse) {
                         }
                     );
                 } 
    	
                 );
    } 
	
    
  //function for joining a forum
    self.joinforum = function (frm) {

		self.fmember.user = $rootScope.user;
		self.fmember.forum = frm;
		
        console.log('in join forum method controller');
         //calling the addBlog method in the factory
        forumFactory.joinforum(self.fmember)
            .then (
                function(resp) {
                    self.fmember =  resp;
                    $route.reload();
                }, function (errResponse) {
                }
            );
         console.log('end of join forum method controller');
    }
    
    //Function to fetch the list of participated users
    function getParticipatedUsers() {
        var deferred = $q.defer();
        var forumId = $routeParams.id;
        forumFactory.getParticipatedUsers(forumId)
            .then (
                function(participatedUsers) {
                    
                 deferred.resolve(participatedUsers);
                },
                function(errResponse) {
                }
            );

            return deferred.promise;
    }
    
 // forum list method
    function myforumlist() {
    	
    	console.log('inside forum list method');
    	var userid = $rootScope.user.userid;
    	forumFactory.myforumlist(userid)
           .then (
               function(forums) {   
                   self.myforumlist = forums;
                   console.log(self.forumlist);
               },
               function(errResponse) {
                   console.log('forum list Failure!');
               }
           );
        console.log('End of forum list method');
   }
    
    // forum post add method
    self.addFpost = function () {
    	
    	 self.fpost.user = $rootScope.user;
    	 self.fpost.fpdate = new Date();
    	 var forumId = $routeParams.id;
    	 self.fpost.forum = $scope.vforum;
        console.log('in fpost add controller');
       // console.log('********blogid '+ self.bcomm.blogid);
       // console.log('*******blogid of blog'+ self.blog.blogid);
         //calling the addBlog comm method in the factory
        fpostFactory.addFpost(self.fpost)
            .then (
                function(forumpost) {
                    self.fpost =  forumpost;
                    
                    $route.reload();
                }, function (errResponse) {
                	
                }
            );
         console.log('end of fpost add controller');
    }
    

    
	// forum list method
    function fpostsByForumid() {
    	
    	var frmid = $routeParams.id;
    	
    	console.log('inside fpost list method');
    	fpostFactory.fpostsByForumid(frmid)
           .then (
               function(forumposts) {   
                   self.fpostsByForumid = forumposts;
                   console.log(self.forumposts);
               },
               function(errResponse) {
                   console.log('fpost list Failure!');
               }
           );
        console.log('End of fpost list method');
   }
    
    
 // calling fmemberlist method 
	nafmemberlist();
    
	// fmember list method
    function nafmemberlist() {
    	
    	console.log('inside nafmemberlist method');
    	forumFactory.nafmemberlist()
           .then (
               function(fmembers) {   
                   self.nafmemberlist = fmembers;
                   console.log(self.nafmemberlist);
               },
               function(errResponse) {
                   console.log('nafmemberlist Failure!');
               }
           );
        console.log('End of nafmemberlist method');
   }
    
  //Function to approve an fmember
    self.fmemberAppr = function(fmemberid) {      
    	forumFactory.fmemberAppr(fmemberid)
            .then (
                function(fmember) {
                    $route.reload();
                },
                function(errResponse) {
                }
            );
    }
    
    //Function to disapprove an fmember
    self.fmemberDisAppr = function(fmemberid) {      
    	forumFactory.fmemberDisAppr(fmemberid)
            .then (
                function(fmember) {
                    $route.reload();
                },
                function(errResponse) {
                }
            );
    }
    
 // calling method 
    joinedforumlist();
    
	// joined forum list method
    function joinedforumlist() {
    	
    	var usrid = $rootScope.user.userid;
    	
    	console.log('inside joinedforumlist method');
    	forumFactory.joinedforumlist(usrid)
           .then (
               function(myforums) {   
                   self.joinedforumlist = myforums;
                   console.log(self.joinedforumlist);
               },
               function(errResponse) {
                   console.log('joinedforumlist Failure!');
               }
           );
        console.log('End of joinedforumlist method');
   }
    
    // calling method 
    createdforumlist();
    
	// created forum list method
    function createdforumlist() {
    	
    	var usrid = $rootScope.user.userid;
    	
    	console.log('inside createdforumlist method');
    	forumFactory.createdforumlist(usrid)
           .then (
               function(cforums) {   
                   self.createdforumlist = cforums;
                   console.log(self.createdforumlist);
               },
               function(errResponse) {
                   console.log('createdforumlist Failure!');
               }
           );
        console.log('End of createdforumlist method');
   }
    
    /***********************/
}]);