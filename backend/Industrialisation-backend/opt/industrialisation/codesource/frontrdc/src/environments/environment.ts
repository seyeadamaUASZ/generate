// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,
  url: 'http://localhost:4300/',
  apii: 'http://localhost:9090/',
  Orbus:'http://196.207.202.51:8080/',
  //apii: 'http://10.3.80.98:8081/industrialisationbackend/',
 //jbpm:'https://192.168.2.98:8081/business-central/',

 //jbpm:'http://10.3.80.98:8080/business-central/',
 jbpm:'http://10.3.20.62:8082/business-central/',
  jbpmdoc: 'http://10.3.20.62:8082/'
 //jbpmdoc: 'http://10.3.80.98:8080/'
 //jbpmdoc: 'https://192.168.2.98:8081/'
};
