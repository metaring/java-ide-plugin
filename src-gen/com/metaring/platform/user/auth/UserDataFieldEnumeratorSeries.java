package com.metaring.platform.user.auth;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.apache.calcite.linq4j.Enumerable;
import org.apache.calcite.linq4j.Linq4j;
import com.metaring.framework.GeneratedCoreType;
import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;

public class UserDataFieldEnumeratorSeries extends ArrayList<UserDataFieldEnumerator> implements GeneratedCoreType {

    private static final long serialVersionUID = 1L;
    private Enumerable<UserDataFieldEnumerator> internalEnumerable;

    private UserDataFieldEnumeratorSeries(Iterable<UserDataFieldEnumerator> iterable) {
        super();
        this.addAll(StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList()));
    }

    public static UserDataFieldEnumeratorSeries create(Iterable<UserDataFieldEnumerator> iterable) {
        return new UserDataFieldEnumeratorSeries(iterable);
    }

    public static UserDataFieldEnumeratorSeries create(UserDataFieldEnumerator... userDataFields) {
        return create(userDataFields == null ? new ArrayList<>() : Arrays.asList(userDataFields));
    }

    public UserDataFieldEnumerator[] toArray() {
        return this.toArray(new UserDataFieldEnumerator[this.size()]);
    }

    public Enumerable<UserDataFieldEnumerator> asEnumerable() {
        return internalEnumerable != null ? internalEnumerable : (internalEnumerable = Linq4j.asEnumerable(this));
    }

    public boolean addAll(Enumerable<UserDataFieldEnumerator> enumerable) {
        return enumerable == null ? false : this.addAll(enumerable.toList());
    }

    public boolean containsAll(Enumerable<UserDataFieldEnumerator> enumerable) {
        return enumerable == null ? false : this.containsAll(enumerable.toList());
    }

    public boolean removeAll(Enumerable<UserDataFieldEnumerator> enumerable) {
        return enumerable == null ? false : this.removeAll(enumerable.toList());
    }

    public boolean retainAll(Enumerable<UserDataFieldEnumerator> enumerable) {
        return enumerable == null ? false : this.retainAll(enumerable.toList());
    }

    public boolean addAll(UserDataFieldEnumerator[] array) {
        return array == null ? false : this.addAll(Arrays.asList(array));
    }

    public boolean containsAll(UserDataFieldEnumerator[] array) {
        return array == null ? false : this.containsAll(Arrays.asList(array));
    }

    public boolean removeAll(UserDataFieldEnumerator[] array) {
        return array == null ? false : this.removeAll(Arrays.asList(array));
    }

    public boolean retainAll(UserDataFieldEnumerator[] array) {
        return array == null ? false : this.retainAll(Arrays.asList(array));
    }

    private void recreateEnumerable() {
        if (internalEnumerable != null) {
            internalEnumerable = Linq4j.asEnumerable(this);
        }
    }

    @Override
    public boolean add(UserDataFieldEnumerator e) {
        boolean test = super.add(e);
        recreateEnumerable();
        return test;
    }

    @Override
    public void add(int index, UserDataFieldEnumerator element) {
        super.add(index, element);
        recreateEnumerable();
    }

    @Override
    public boolean addAll(Collection<? extends UserDataFieldEnumerator> c) {
        boolean test = super.addAll(c);
        recreateEnumerable();
        return test;
    }

    @Override
    public boolean addAll(int index, Collection<? extends UserDataFieldEnumerator> c) {
        boolean test = super.addAll(index, c);
        recreateEnumerable();
        return test;
    }

    @Override
    public boolean remove(Object o) {
        boolean test = super.remove(o);
        recreateEnumerable();
        return test;
    }

    @Override
    public UserDataFieldEnumerator remove(int index) {
        UserDataFieldEnumerator test = super.remove(index);
        recreateEnumerable();
        return test;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean test = super.removeAll(c);
        recreateEnumerable();
        return test;
    }

    @Override
    public boolean removeIf(Predicate<? super UserDataFieldEnumerator> filter) {
        boolean test = super.removeIf(filter);
        recreateEnumerable();
        return test;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean test = super.retainAll(c);
        recreateEnumerable();
        return test;
    }

    @Override
    public void replaceAll(UnaryOperator<UserDataFieldEnumerator> operator) {
        super.replaceAll(operator);
        recreateEnumerable();
    }

    public static UserDataFieldEnumeratorSeries fromJson(String jsonString) {
        if (jsonString == null) {
            return null;
        }
        jsonString = jsonString.trim();
        if(jsonString.isEmpty()) {
            return null;
        }
        if(jsonString.equalsIgnoreCase("null")) {
            return null;
        }

        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.fromJson(jsonString);
        UserDataFieldEnumerator[] values = new UserDataFieldEnumerator[dataRepresentation.length()];
        for(int i = 0; i < values.length; i++) {
            values[i] = dataRepresentation.get(i, UserDataFieldEnumerator.class);
        }
        return create(values);
    }

    @Override
    public String toString() {
        return toJson();
    }

    @Override
    public String toJson() {
        StringBuilder sb = new StringBuilder("[");
        forEach(it -> sb.append(it.toJson()).append(","));
        return sb.delete(sb.length() - 1, sb.length()).append("]").toString();
    }

    public DataRepresentation toDataRepresentation() {
        return Tools.FACTORY_DATA_REPRESENTATION.fromJson(toJson());
    }
}