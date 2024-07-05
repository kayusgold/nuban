var restify = require("restify");
var config = require("./config/config");
const restifyPlugins = require("restify-plugins");
// var route_link = require("./routes/route_index");
var restify_err = require("restify-errors");

const server = restify.createServer({
  name: config.name,
  version: config.version
});

server.use(
  restifyPlugins.jsonBodyParser({
    mapParams: true
  })
);

server.use(restifyPlugins.acceptParser(server.acceptable));

server.use(
  restifyPlugins.queryParser({
    mapParams: true
  })
);

server.use(restifyPlugins.fullResponse());

server.listen(config.port, function() {
  console.log("%s listening at %s", server.name, config.base_url);
});

// route_link(server);
server.get('/health', (req, res, next) => {
    res.send(200, {status: 'UP'});
  next();
});

var nubanUtil = require("./utils/nuban_2011_util");

server.get("/accounts/:account(^\\d{10}$)/banks", (req, res, next) => {
    // nubanUtil.getAccountBanks(req, res, next);
    nubanUtil.getAccountBanks2020(req, res, next);
  });

  server.post("/banks/:bank(^\\d{3}$)/accounts", (req, res, next) => {
    nubanUtil.createAccountWithSerial(req, res, next);
  });