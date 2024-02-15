package com.atm.feign.listener.models;

import lombok.*;

/**
 *  By declaring "exclude" attribute in @ToString and @EqualsAndHashCode annotation
 *  we can exclude property from a class.
 *  Example : @ToString(exclude = "createdDate")
 *            @EqualsAndHashCode(exclude = "createdDate")
 *            ("createDate" must be a field/variable/property defined in Pojo class, in this case it is not)
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class BooksBook {

    private int id;
    private int downloadCount;
    private int gutenbergId;
    private String mediaType;
    private String title;
}
