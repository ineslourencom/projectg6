package switchtwentyone.project.domain.aggregates.account;

import switchtwentyone.project.domain.aggregates.profile.ProfileID;
import switchtwentyone.project.domain.aggregates.profile.ProfileType;
import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequest;
import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequestCreatable;
import switchtwentyone.project.domain.aggregates.profilerequest.ProfileRequestID;
import switchtwentyone.project.domain.shared.Entity;
import switchtwentyone.project.domain.valueObjects.NoNumberNoSymbolString;

import java.util.ArrayList;
import java.util.List;

public class Account implements Entity<Account> {

    /**
     * Constructor method for Account.
     * It allows for the creation of an account with the initial profile.
     *
     * @param accountID id vo
     * @param email email vo
     * @param name name vo
     * @param function function vo
     * @param photo photo vo
     * @param password password vo
     * @param profileIDs list of profile IDs vo
     */
    private final AccountID accountID;
    private Email email;
    private NoNumberNoSymbolString name;
    private NoNumberNoSymbolString function;
    private Photo photo;
    private Password password;
    private List<ProfileID> profileIDs;
    private boolean active;

    public Account(final AccountID accountID,
                      final Email email,
                      final NoNumberNoSymbolString name,
                      NoNumberNoSymbolString function,
                      Photo photo,
                      Password password,
                      ProfileID profileID) {

        validateNotNull(accountID, email, name, function, photo, password);
        validateNotNullProfileID(profileID);

        this.accountID = accountID;
        this.email = email;
        this.name = name;
        this.function = function;
        this.photo = photo;
        this.password = password;
        this.profileIDs = new ArrayList<>();

        this.profileIDs.add(profileID);
        this.active = false;
    }

    /**
     * This is the JPA invoked constructor of Account.
     * It allows for the instancing of an account with an existing list
     * of profiles.
     *
     * @param accountID  id from converted String storage
     * @param email      email from converted String storage
     * @param name       name from converted String storage
     * @param function   function from converted String storage
     * @param photo      photo from converted String storage
     * @param password   password from converted String storage
     * @param profileIDs list of profile IDs from converted String storage
     */
    protected Account(final AccountID accountID,
                      final Email email,
                      final NoNumberNoSymbolString name,
                      NoNumberNoSymbolString function,
                      Photo photo,
                      Password password,
                      List<ProfileID> profileIDs,
                      boolean active) {

        validateNotNull(accountID, email, name, function, photo, password);
        validateNotNullListProfileIDs(profileIDs);

        this.accountID = accountID;
        this.email = email;
        this.name = name;
        this.function = function;
        this.photo = photo;
        this.password = password;
        this.profileIDs = new ArrayList<>(profileIDs);
        this.active = active;
    }

    /**
     * NotNull validation method for some attributes of Account.
     *
     * @param accountIDData id object
     * @param emailData     email object
     * @param nameData      name object
     * @param functionData  function object
     * @param photoData     photo object
     * @param passwordData  password object
     */
    private void validateNotNull(final AccountID accountIDData,
                                 final Email emailData,
                                 final NoNumberNoSymbolString nameData,
                                 final NoNumberNoSymbolString functionData,
                                 final Photo photoData,
                                 final Password passwordData) {

        if (accountIDData == null) {
            throw new IllegalArgumentException("AccountID is required");
        }
        if (emailData == null) {
            throw new IllegalArgumentException("Email is required");
        }
        if (nameData == null) {
            throw new IllegalArgumentException("Name is required");
        }
        if (functionData == null) {
            throw new IllegalArgumentException("Function is required");
        }
        if (photoData == null) {
            throw new IllegalArgumentException("Photo is required");
        }
        if (passwordData == null) {
            throw new IllegalArgumentException("Password is required");
        }
    }

    /**
     * NotNull validation method for ProfileID attribute of Account.
     * Attribute validated:
     * .ProfileID
     *
     * @param profileIDData profile ID object
     */
    private void validateNotNullProfileID(final ProfileID profileIDData) {
        if (profileIDData == null) {
            throw new IllegalArgumentException("ProfileID is required");
        }
    }

    /**
     * NotNull validation method for ProfileID attribute of Account.
     * Attribute validated:
     * .ProfileID
     *
     * @param profileIDData list of profile IDs from converted String storage
     */
    private void validateNotNullListProfileIDs(final List<ProfileID> profileIDData) {
        if (profileIDData == null) {
            throw new IllegalArgumentException("ProfileIDs are required");
        }
        if (profileIDData.isEmpty()) {
            throw new IllegalArgumentException("A ProfileID is required");
        }
    }

    /**
     * Getter method for Account identifier.
     * The AccountID is the identity of this entity, and it is unique.
     *
     * @return AccountID.
     */
    public AccountID getAccountID() {
        return this.accountID;
    }

    /**
     * Getter method for Account email.
     * The email is unique.
     *
     * @return email.
     */
    public Email getEmail() {
        return this.email;
    }

    /**
     * Getter method for Account name.
     * The name is the identity of the user.
     *
     * @return Name.
     */
    public NoNumberNoSymbolString getName() {
        return name;
    }

    /**
     * Getter method for Account function.
     * The function is the user´s job in the company.
     *
     * @return function.
     */
    public NoNumberNoSymbolString getFunction() {
        return function;
    }

    /**
     * Getter method for Account photo.
     * The attribute is the user´s photograph (in String form).
     *
     * @return photo.
     */
    public Photo getPhoto() {
        return photo;
    }

    /**
     * Getter method for Account password.
     * This method should be eliminated and substituted by the comparing method
     * of passwords in string (non digest) form.
     * Please revise the AccountRepo Tests for update().
     *
     * @return password hash.
     */
    public Password getPassword() {
        return password;
    }

    /*
    Returns the last profile added to the list.
     */
    public ProfileID getActiveProfileID() {
        return this.profileIDs.get(this.profileIDs.size() - 1);
    }

    public List<ProfileID> getProfileIDs() {
        return new ArrayList<>(profileIDs);
    }

    public boolean getActive(){
        return this.active;
    }

    /**
     * Setter method for Account function.
     * The function is the user´s job in the company.
     */
    public void setFunction(NoNumberNoSymbolString newFunction) {
        this.function = newFunction;
    }

    /**
     * Setter method for Account photo.
     * The attribute is the user´s photo.
     */
    public void setPhoto(Photo newPhoto) {
        this.photo = newPhoto;
    }

    /**
     * Method used to verify if a certain account is the same as the one
     * identified by an email passed as parameter.
     *
     * @param email address given as account identifier
     * @return true if accounts are the same, false if otherwise.
     */
    public boolean emailExists(Email email) {
        return this.email.sameValueAs(email);
    }

    public ProfileRequest createRequest(Email accountID, ProfileType profileType) {
        ProfileRequestID profileRequestID = ProfileRequestID.createProfileRequestID();

        return ProfileRequestCreatable.createProfileRequest(profileRequestID, accountID, profileType);
    }

    /**
     * Method that add's a new unique profile to account.
     *
     * @param profileID
     */
    public void addProfile(ProfileID profileID) {

        boolean unique = true;

        for (ProfileID p : profileIDs) {
            if (p == profileID) {
                unique = false;
            }
        }
        if (unique) {
            this.profileIDs.add(profileID);
        }
    }

    /**
     * Method that checks if an account has a given profile.
     *
     * @param profileID profile identifier to check for
     * @return true if account has given profile, false if otherwise
     */
    public boolean existsProfile(ProfileID profileID) {
        boolean exists = false;
        for (ProfileID p : profileIDs) {
            if (p.sameValueAs(profileID)) {
                exists = true;
            }
        }
        return exists;
    }


    /**
     * Override methods for Account.
     * Fall back to the sameIdentityAs method for equals evaluation because
     * it is an Entity (ID attributes must be equal).
     */
    @Override
    public boolean sameIdentityAs(final Account other) {
        return other != null && this.accountID.sameValueAs(other.accountID);
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        final Account other = (Account) object;
        return sameIdentityAs(other);
    }

    @Override
    public int hashCode() {
        return accountID.hashCode();
    }

    @Override
    public String toString() {
        return accountID.toString();
    }

    /**
     * The following code lines were copied from Eric Evans DDD example, but we don't know yet what will be their utility

     Account() {
     // Needed by Hibernate
     }

     // Auto-generated surrogate key
     private Long id;

     */


}
