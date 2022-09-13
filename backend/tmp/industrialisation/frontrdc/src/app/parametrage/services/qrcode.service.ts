import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Qrcodes } from '../models/qrcodes';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QrcodeService {
  api = environment.apii;
  constructor(private http: HttpClient) { }

  saveQrcode(qrcode:Qrcodes) {
      return this.http.post<any>(this.api + 'barcodes/qrcode',qrcode);
  }

generateQrcode(contenue:any):Observable<Blob> {
  return this.http.post<any>(this.api + 'barcodes/zxing/qrcode',contenue);
}
getallQrcode(){
  return this.http.get<any>(this.api + 'barcodes/listqrcodes');
}
getimage(id){
  return this.http.get<any>(this.api + 'barcodes/'+id);
}

download(id){
  let headers = new HttpHeaders();
  headers = headers.append('Accept', 'image/png');
  return this.http.get(this.api + 'barcodes/downloadFile/'+id, 
  {
    headers: headers,
    observe: 'response',
    responseType:'arraybuffer'});
}

}
