# BananaBank
GET /balance/user/<userId>	
		
	Content-Type: application/json	
		
	{ “value”: 400 }	

HTTP 200 OK	Returns the current balance value for the specified user.


GET /history/user/<userId>	

	Content-Type: application/json	
	[	
	{ 	
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
	}	
	]	
	}	

HTTP 200 OK	Returns the history of the transactions. The array is ordered from the first transaction.


POST /balance/increase/user/<userId>	
	Headers:		
	Content-Type: application/json		
	Body:		
	{ 		
	“value”: 100		
	}		

HTTP 200 OK	Performs balance increase operation for the specified user.


POST /balance/decrease/user/<userId>	

	Headers:		
	Content-Type: application/json		
	Body:		
	{ 		
	“value”: 100,		
	“token”: “3a81de”		
	}		
	
HTTP 200 OK	Performs balance decrease operation for the specified user. The operation is secured using one time password generated using the other endpoint.


POST /tokens/user/<userId>	

	Content-Type: application/json	
		
		
	{ “token”: “3a81de” }	
	
HTTP 201 Created	Generates one time password that can be used to secure other operations.
