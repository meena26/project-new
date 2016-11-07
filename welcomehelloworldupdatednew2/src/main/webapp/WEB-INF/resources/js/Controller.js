var app=angular.module("myapp",[])
.controller("bookController",function($scope,$http){

	$scope.getBooks = function(){
			
		   $http.get('http://localhost:8080/welcomehelloworld/getBooksList').success(function (data){
		       $scope.books = data;
		   });
		};
		$scope.addToCart=function(isbn){
			   $http.put('http://localhost:8080/welcomehelloworld/cart/add/'+isbn).success(function(){
				   alert('Added Successfully');
			   })
		   }
		$scope.refreshCart=function(){
			$http.get('http://localhost:8080/welcomehelloworld/cart/getCart/'+$scope.cartId).success(function(data){
				$scope.cart=data;
			})
		    } 
		    $scope.getCart=function(cartId){
			$scope.cartId=cartId;
			$scope.refreshCart(cartId);
		    }

		    $scope.removeFromCart=function(cartItemId){
				$http.put(
		'http://localhost:8080/welcomehelloworld/cart/removecartitem/'+cartItemId)
			.success(function(){
				$scope.refreshCart();
			})
		    }

		    $scope.clearCart=function(){
				$http.put(
		'http://localhost:8080/welcomehelloworld/cart/removeAllItems/'+$scope.cartId)
			.success(function(){
				$scope.refreshCart();
			});
		    }

		    $scope.calculateGrandTotal=function(){
		    	var grandTotal=0.0
		    	for(var i=0;i<$scope.cart.cartItems.length;i++)
		    		grandTotal=grandTotal+$scope.cart.cartItems[i].totalPrice;
		    	return grandTotal;
		    }


		});



