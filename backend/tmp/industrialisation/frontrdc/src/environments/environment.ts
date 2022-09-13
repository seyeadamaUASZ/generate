// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: true,
  url: 'http://localhost:4300/',
  apii: 'http://localhost:9090/',
  //apii: 'http://10.3.80.98:8080/industrialisationbackend/'  
  jbpm:'http://10.3.80.98:8080/business-central'
};
