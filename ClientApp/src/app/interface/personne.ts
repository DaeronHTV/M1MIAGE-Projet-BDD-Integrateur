export interface PersonneInterface {
    id?: string,
    nom: string,
    prenom: string,
}

export interface UtilisateurInterface extends PersonneInterface {
    id?: string,
    numeroTelephone: string,
    adresseMail: string,
}

export interface ResidentInterface extends PersonneInterface {
    id?: string,
    idUnite?: string,
    idPersonnel?: string,
    dateNaissance: string,
    statut: ResidentStatutEnum | ResidentStatutEnum.Disponible,
}

export interface PersonnelInterface extends UtilisateurInterface {
    id?: string,
    fonction: string,
}

export interface ContactInterface extends PersonneInterface {
    id?:string
}

export enum ResidentStatutEnum {
    Disponible = "DISPONIBLE",
    Indisponible = "INDISPONIBLE",
    AncienResident = "ANCIENRESIDENT"
}