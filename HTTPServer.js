var dispatcher = require('httpdispatcher');
var http = require('http');
var pg = require('pg');
var url = require('url');
var SQL = require('j-sql');

const PORT=8080; 
var conString = "postgres://postgres@localhost/Assuistant";

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

dispatcher.setStatic('resources');

dispatcher.onGet("/ticks",function(req,res){
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
}); 

dispatcher.onGet("/log",function(req,res){
	var client = new pg.Client(conString);
	client.connect(function(err) {
		if(err) {
			return console.error('could not connect to postgres', err);
		}
		client.query('SELECT * FROM public.log', function(err, result) {
			if(err) {
			  return console.error('error running query', err);
			}
			res.writeHead(200, {'Content-Type': 'application/json'});
			res.end(JSON.stringify(result));
			client.end();
		});
	});
}); 

dispatcher.onGet("/employee",function(req,res){
	var client = new pg.Client(conString);
	client.connect(function(err) {
		if(err) {
			return console.error('could not connect to postgres', err);
		}
		client.query('SELECT * FROM public.employee', function(err, result) {
			if(err) {
			  return console.error('error running query', err);
			}
			res.writeHead(200, {'Content-Type': 'application/json'});
			res.end(JSON.stringify(result));
			client.end();
		});
	});
}); 

dispatcher.onPost("/test",function(req,res){
	var querydata = url.parse(req.url, true).query;
	var client = new pg.Client(conString);
	querydata.id = parseInt(querydata.id);
	var sql = SQL.insert('public.log').values(querydata).done();
	console.log(sql);
	var sql_script="";
	for(var i = 0, len = sql.length; i<len;i++){
		if(sql[i] == '\"' ){
			sql_script = sql_script + '\'';
		}
		else{
			sql_script=sql_script+sql[i];
		}
	}
	client.connect(function(err) {
		if(err) {
			return console.error('could not connect to postgres', err);
		}
		client.query(sql_script, function(err, result) {
			if(err) {
				return console.error('error running query', err);
			}
			client.end();
		});
	});
	res.writeHead(200, {'Content-Type': 'application/json'});
	res.end(JSON.stringify(querydata));
});

//Create a server
var server = http.createServer(handleRequest);

//start server
server.listen(PORT, "0.0.0.0", function(){
    //Callback triggered when server is successfully listening.
    console.log("Server listening on: http://localhost:%s", PORT);
});
