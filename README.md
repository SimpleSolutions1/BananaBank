# BananaBank
<<<<<<< HEAD
GET /balance/user/{userId}	
		
	Content-Type: application/json	
		
	{ "value": 400 }	
=======
GET /balance/user/<userId>	
		
	Content-Type: application/json	
		
	{ “value”: 400 }	
>>>>>>> e58556419f0b8c887858a9df166bcf2a81f9b2d7

HTTP 200 OK	Returns the current balance value for the specified user.


<<<<<<< HEAD
GET /history/user/{userId}	
=======
GET /history/user/<userId>	
>>>>>>> e58556419f0b8c887858a9df166bcf2a81f9b2d7

	Content-Type: application/json	
	[	
	{ 	
<<<<<<< HEAD
	"type":"increase",	
	"value":600,	
	},	
	{ 	
	"type":"increase",	
	"value":300,	
	},	
		
	{ 	
	"type":"decrease",	
	"value":500,	
=======
	“type”:“increase”,	
	“value”:600,	
	},	
	{ 	
	“type”:“increase”,	
	“value”:300,	
	},	
		
	{ 	
	“type”:“decrease”,	
	“value”:500,	
>>>>>>> e58556419f0b8c887858a9df166bcf2a81f9b2d7
	}	
	]	
	}	

HTTP 200 OK	Returns the history of the transactions. The array is ordered from the first transaction.


<<<<<<< HEAD
POST /balance/increase/user/{userId}	

=======
POST /balance/increase/user/<userId>	
>>>>>>> e58556419f0b8c887858a9df166bcf2a81f9b2d7
	Headers:		
	Content-Type: application/json		
	Body:		
	{ 		
<<<<<<< HEAD
	"value": 100		
=======
	“value”: 100		
>>>>>>> e58556419f0b8c887858a9df166bcf2a81f9b2d7
	}		

HTTP 200 OK	Performs balance increase operation for the specified user.


<<<<<<< HEAD
POST /balance/decrease/user/{userId}	
=======
POST /balance/decrease/user/<userId>	
>>>>>>> e58556419f0b8c887858a9df166bcf2a81f9b2d7

	Headers:		
	Content-Type: application/json		
	Body:		
	{ 		
<<<<<<< HEAD
	"value": 100,		
	"token": "3a81de"		
=======
	“value”: 100,		
	“token”: “3a81de”		
>>>>>>> e58556419f0b8c887858a9df166bcf2a81f9b2d7
	}		
	
HTTP 200 OK	Performs balance decrease operation for the specified user. The operation is secured using one time password generated using the other endpoint.


<<<<<<< HEAD
POST /tokens/user/{userId}	

	Content-Type: application/json	
	{ "token": "3a81de" }	
=======
POST /tokens/user/<userId>	

	Content-Type: application/json	
		
		
	{ “token”: “3a81de” }	
>>>>>>> e58556419f0b8c887858a9df166bcf2a81f9b2d7
	
HTTP 201 Created	Generates one time password that can be used to secure other operations.
