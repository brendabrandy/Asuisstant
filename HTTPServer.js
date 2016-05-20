var dispatcher = require('httpdispatcher');
var http = require('http');
var pg = require('pg');

//Lets define a port we want to listen to
const PORT=8080; 

//Lets use our dispatcher
function handleRequest(request, response){
    try {
        //log the request on console
        console.log(request.url);
        //Disptach
        dispatcher.dispatch(request, response);
    } catch(err) {
        console.log(err);
    }
}

//For all your static (js/css/images/etc.) set the directory name (relative path).
dispatcher.setStatic('resources');

//A sample GET request    
dispatcher.onGet("/ticker",function(req,res){
	var conString = "postgres://postgres@localhost/Assuistant";
	var client = new pg.Client(conString);
	client.connect(function(err) {
	  if(err) {
		return console.error('could not connect to postgres', err);
	  }
	  client.query('SELECT * FROM public.ticks', function(err, result) {
		if(err) {
		  return console.error('error running query', err);
		}
		res.writeHead(200, {'Content-Type': 'application/json'});
		res.end(JSON.stringify(result));
		client.end();
	  });
	});
    //res.writeHead(200, {'Content-Type': 'text/plain'});
    //res.end('Page One');
}); 

//A sample POST request
dispatcher.onPost("/post1", function(req, res) {
    res.writeHead(200, {'Content-Type': 'text/plain'});
    res.end('Got Post Data');
});


//Create a server
var server = http.createServer(handleRequest);

//Lets start our server
server.listen(PORT, "0.0.0.0", function(){
    //Callback triggered when server is successfully listening. Hurray!
    console.log("Server listening on: http://localhost:%s", PORT);
});
