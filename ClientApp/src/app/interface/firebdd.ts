export interface CompteInterface {
    id?: string,
    nom: string,
    prenom: string,
    telephone: string,
    mail: string,
    profilePicture?: string,
    dateCreation?: string,
    verified?: boolean | false
    resident?: {
        nom: string,
        prenom: string,
        etablissement: string,
        dateNaissance: string,
    }
}

export interface QueryCompte{
    id?: string | null,
    nom?: string | null,
    prenom?: string | null,
    telephone?: string | null,
    mail: string | null,
    dateCreation?: string | null,
    verified?: boolean | false ,
    resident?: {
        nom: string,
        prenom: string,
        etablissement: string,
        dateNaissance: string,
    }
}

export interface ModifCompteInterface {
    id?: string | null,
    nom?: string | null,
    prenom?: string | null,
    telephone?: string | null,
    mail?: string | null,
    profilePicture?: string | null,
    dateCreation?: string | null,
    verified?: boolean | false
}
