import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { NotificationMessage } from '../models/notification';

@Injectable({
    providedIn: 'root'
})

export class NotificationServiceMessage {
    api = environment.apii;
    constructor(private http: HttpClient) { }
    
    listeNotifications () {
        return this.http.get<any>(this.api + 'notification');
    }

    listeEvenements (notification: any) {
        return this.http.post<any>(this.api + 'evenement/evenementparnotification', notification);
    }

    allocateEvenements(notification:NotificationMessage, removed:any[], added:any[]) {    
        let formData = new FormData();
        formData.append("notification", JSON.stringify(notification));
        formData.append("removed", JSON.stringify(removed));
        formData.append("added", JSON.stringify(added));
        return this.http.post<any>(this.api + 'evenement/allocateevenement', formData);
    }

    listeProfils() {
        return this.http.get<any>(this.api + 'profiles');
    }

    listeDestinataireParProfils(profil: any) {
        return this.http.post<any>(this.api + 'users/listUsersbyprofil', profil);
    }

    listeDestinataires() {
        return this.http.get<any>(this.api + 'users');
    }

    allocateDestinataires(notification:NotificationMessage, removed:any[], added:any[]) {
        let formData = new FormData();
        formData.append("notification", JSON.stringify(notification));
        formData.append("removed", JSON.stringify(removed));
        formData.append("added", JSON.stringify(added));
        return this.http.post<any>(this.api+ 'notificationdestionataire/allocateDestinataire', formData);
    }

    listeTypeNotification() {
        return this.http.get<any>(this.api + 'typenotification');
    }

    addNotificationParProfil (profil, notification:NotificationMessage) {
        let formatData = new FormData;
        formatData.append("profil", JSON.stringify(profil));
        formatData.append("notification", JSON.stringify(notification));
        return this.http.post<any>(this.api + 'notification/addparprofil', formatData);
    }

    addNotificationAudioParProfil (profil, notification:NotificationMessage, file:any) {
        let formatData = new FormData;
        formatData.append("profil", JSON.stringify(profil));
        formatData.append("notification", JSON.stringify(notification));
        formatData.append('audio', file);
        return this.http.post<any>(this.api + 'notification/addaudioparprofil', formatData);
    }
}