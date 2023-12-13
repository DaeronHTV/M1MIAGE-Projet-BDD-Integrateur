export interface rendezVousInterface {
    id?: string,
    dateRDV: Date,
    heureRDV: Date,
    etatRDV:string,
    statusRDV:string,
    comment : string,
    avis: number,
    nomResident: string,
    prenomResident : string,
    Invites : {invite:InvitesInterface}[] | [], 
}

export interface InvitesInterface {
    id?: string,
    nomInvite: string,
    prenomInvite: string,
    emailInvite: string,
}

export interface CreneauGenInterface {
    creneau : CreneauInterface,
}

export interface CreneauInterface {
    idCreneau?: string,
    idPersonnel: string,
    dateCreneau: Date,
    heureCreneau: string,
    disponisble: number,
    dureeCreneau: number,
}
