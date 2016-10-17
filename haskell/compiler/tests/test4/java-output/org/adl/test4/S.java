package org.adl.test4;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.adl.runtime.Factories;
import org.adl.runtime.Factory;
import org.adl.runtime.HashMapHelpers;
import org.adl.runtime.HashSetHelpers;
import org.adl.runtime.JsonBinding;
import org.adl.runtime.JsonBindings;
import org.adl.runtime.OptionalHelpers;
import org.adl.sys.types.Pair;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

public class S {

  /* Members */

  private Date v1;
  private Date v2;
  private CDate v3;
  private CDate v4;
  private Optional<String> v5;
  private Optional<String> v5a;
  private Optional<String> v5b;
  private Pair<String, Integer> v6;
  private HashSet<Integer> v7;
  private HashSet<Integer> v7a;
  private HashMap<String, Integer> v8;
  private HashMap<String, Integer> v8a;

  /* Constructors */

  public S(Date v1, Date v2, CDate v3, CDate v4, Optional<String> v5, Optional<String> v5a, Optional<String> v5b, Pair<String, Integer> v6, HashSet<Integer> v7, HashSet<Integer> v7a, HashMap<String, Integer> v8, HashMap<String, Integer> v8a) {
    this.v1 = Objects.requireNonNull(v1);
    this.v2 = Objects.requireNonNull(v2);
    this.v3 = Objects.requireNonNull(v3);
    this.v4 = Objects.requireNonNull(v4);
    this.v5 = Objects.requireNonNull(v5);
    this.v5a = Objects.requireNonNull(v5a);
    this.v5b = Objects.requireNonNull(v5b);
    this.v6 = Objects.requireNonNull(v6);
    this.v7 = Objects.requireNonNull(v7);
    this.v7a = Objects.requireNonNull(v7a);
    this.v8 = Objects.requireNonNull(v8);
    this.v8a = Objects.requireNonNull(v8a);
  }

  public S() {
    this.v1 = new Date();
    this.v2 = new Date("2000-01-01");
    this.v3 = new CDate();
    this.v4 = new CDate((short)2000, (short)1, (short)1);
    this.v5 = OptionalHelpers.factory(Factories.STRING).create();
    this.v5a = OptionalHelpers.nothing(null);
    this.v5b = OptionalHelpers.just("hello");
    this.v6 = Pair.factory(Factories.STRING, Factories.INTEGER).create();
    this.v7 = HashSetHelpers.create(Factories.arrayList(1, 2, 3));
    this.v7a = HashSetHelpers.factory(Factories.INTEGER).create();
    this.v8 = HashMapHelpers.factory(Factories.STRING, Factories.INTEGER).create();
    this.v8a = HashMapHelpers.create(Factories.arrayList(new Pair<String, Integer>("X", 1), new Pair<String, Integer>("Y", 2)));
  }

  public S(S other) {
    this.v1 = Date.FACTORY.create(other.v1);
    this.v2 = Date.FACTORY.create(other.v2);
    this.v3 = CDate.FACTORY.create(other.v3);
    this.v4 = CDate.FACTORY.create(other.v4);
    this.v5 = OptionalHelpers.factory(Factories.STRING).create(other.v5);
    this.v5a = OptionalHelpers.factory(Factories.STRING).create(other.v5a);
    this.v5b = OptionalHelpers.factory(Factories.STRING).create(other.v5b);
    this.v6 = Pair.factory(Factories.STRING, Factories.INTEGER).create(other.v6);
    this.v7 = HashSetHelpers.factory(Factories.INTEGER).create(other.v7);
    this.v7a = HashSetHelpers.factory(Factories.INTEGER).create(other.v7a);
    this.v8 = HashMapHelpers.factory(Factories.STRING, Factories.INTEGER).create(other.v8);
    this.v8a = HashMapHelpers.factory(Factories.STRING, Factories.INTEGER).create(other.v8a);
  }

  /* Accessors and mutators */

  public Date getV1() {
    return v1;
  }

  public void setV1(Date v1) {
    this.v1 = Objects.requireNonNull(v1);
  }

  public Date getV2() {
    return v2;
  }

  public void setV2(Date v2) {
    this.v2 = Objects.requireNonNull(v2);
  }

  public CDate getV3() {
    return v3;
  }

  public void setV3(CDate v3) {
    this.v3 = Objects.requireNonNull(v3);
  }

  public CDate getV4() {
    return v4;
  }

  public void setV4(CDate v4) {
    this.v4 = Objects.requireNonNull(v4);
  }

  public Optional<String> getV5() {
    return v5;
  }

  public void setV5(Optional<String> v5) {
    this.v5 = Objects.requireNonNull(v5);
  }

  public Optional<String> getV5a() {
    return v5a;
  }

  public void setV5a(Optional<String> v5a) {
    this.v5a = Objects.requireNonNull(v5a);
  }

  public Optional<String> getV5b() {
    return v5b;
  }

  public void setV5b(Optional<String> v5b) {
    this.v5b = Objects.requireNonNull(v5b);
  }

  public Pair<String, Integer> getV6() {
    return v6;
  }

  public void setV6(Pair<String, Integer> v6) {
    this.v6 = Objects.requireNonNull(v6);
  }

  public HashSet<Integer> getV7() {
    return v7;
  }

  public void setV7(HashSet<Integer> v7) {
    this.v7 = Objects.requireNonNull(v7);
  }

  public HashSet<Integer> getV7a() {
    return v7a;
  }

  public void setV7a(HashSet<Integer> v7a) {
    this.v7a = Objects.requireNonNull(v7a);
  }

  public HashMap<String, Integer> getV8() {
    return v8;
  }

  public void setV8(HashMap<String, Integer> v8) {
    this.v8 = Objects.requireNonNull(v8);
  }

  public HashMap<String, Integer> getV8a() {
    return v8a;
  }

  public void setV8a(HashMap<String, Integer> v8a) {
    this.v8a = Objects.requireNonNull(v8a);
  }

  /* Object level helpers */

  @Override
  public boolean equals(Object other0) {
    if (!(other0 instanceof S)) {
      return false;
    }
    S other = (S) other0;
    return
      v1.equals(other.v1) &&
      v2.equals(other.v2) &&
      v3.equals(other.v3) &&
      v4.equals(other.v4) &&
      v5.equals(other.v5) &&
      v5a.equals(other.v5a) &&
      v5b.equals(other.v5b) &&
      v6.equals(other.v6) &&
      v7.equals(other.v7) &&
      v7a.equals(other.v7a) &&
      v8.equals(other.v8) &&
      v8a.equals(other.v8a);
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = result * 37 + v1.hashCode();
    result = result * 37 + v2.hashCode();
    result = result * 37 + v3.hashCode();
    result = result * 37 + v4.hashCode();
    result = result * 37 + v5.hashCode();
    result = result * 37 + v5a.hashCode();
    result = result * 37 + v5b.hashCode();
    result = result * 37 + v6.hashCode();
    result = result * 37 + v7.hashCode();
    result = result * 37 + v7a.hashCode();
    result = result * 37 + v8.hashCode();
    result = result * 37 + v8a.hashCode();
    return result;
  }

  /* Factory for construction of generic values */

  public static final Factory<S> FACTORY = new Factory<S>() {
    public S create() {
      return new S();
    }
    public S create(S other) {
      return new S(other);
    }
  };

  /* Json serialization */

  public static JsonBinding<S> jsonBinding() {
    final JsonBinding<Date> v1 = Date.jsonBinding();
    final JsonBinding<Date> v2 = Date.jsonBinding();
    final JsonBinding<CDate> v3 = CDate.jsonBinding();
    final JsonBinding<CDate> v4 = CDate.jsonBinding();
    final JsonBinding<Optional<String>> v5 = OptionalHelpers.jsonBinding(JsonBindings.STRING);
    final JsonBinding<Optional<String>> v5a = OptionalHelpers.jsonBinding(JsonBindings.STRING);
    final JsonBinding<Optional<String>> v5b = OptionalHelpers.jsonBinding(JsonBindings.STRING);
    final JsonBinding<Pair<String, Integer>> v6 = Pair.jsonBinding(JsonBindings.STRING, JsonBindings.INTEGER);
    final JsonBinding<HashSet<Integer>> v7 = HashSetHelpers.jsonBinding(JsonBindings.INTEGER);
    final JsonBinding<HashSet<Integer>> v7a = HashSetHelpers.jsonBinding(JsonBindings.INTEGER);
    final JsonBinding<HashMap<String, Integer>> v8 = HashMapHelpers.jsonBinding(JsonBindings.STRING, JsonBindings.INTEGER);
    final JsonBinding<HashMap<String, Integer>> v8a = HashMapHelpers.jsonBinding(JsonBindings.STRING, JsonBindings.INTEGER);
    final Factory<S> _factory = FACTORY;

    return new JsonBinding<S>() {
      public Factory<S> factory() {
        return _factory;
      }

      public JsonElement toJson(S _value) {
        JsonObject _result = new JsonObject();
        _result.add("v1", v1.toJson(_value.v1));
        _result.add("v2", v2.toJson(_value.v2));
        _result.add("v3", v3.toJson(_value.v3));
        _result.add("v4", v4.toJson(_value.v4));
        _result.add("v5", v5.toJson(_value.v5));
        _result.add("v5a", v5a.toJson(_value.v5a));
        _result.add("v5b", v5b.toJson(_value.v5b));
        _result.add("v6", v6.toJson(_value.v6));
        _result.add("v7", v7.toJson(_value.v7));
        _result.add("v7a", v7a.toJson(_value.v7a));
        _result.add("v8", v8.toJson(_value.v8));
        _result.add("v8a", v8a.toJson(_value.v8a));
        return _result;
      }

      public S fromJson(JsonElement _json) {
        JsonObject _obj = _json.getAsJsonObject();
        return new S(
          _obj.has("v1") ? v1.fromJson(_obj.get("v1")) : new Date(),
          _obj.has("v2") ? v2.fromJson(_obj.get("v2")) : new Date("2000-01-01"),
          _obj.has("v3") ? v3.fromJson(_obj.get("v3")) : new CDate(),
          _obj.has("v4") ? v4.fromJson(_obj.get("v4")) : new CDate((short)2000, (short)1, (short)1),
          _obj.has("v5") ? v5.fromJson(_obj.get("v5")) : OptionalHelpers.factory(Factories.STRING).create(),
          _obj.has("v5a") ? v5a.fromJson(_obj.get("v5a")) : OptionalHelpers.nothing(null),
          _obj.has("v5b") ? v5b.fromJson(_obj.get("v5b")) : OptionalHelpers.just("hello"),
          _obj.has("v6") ? v6.fromJson(_obj.get("v6")) : Pair.factory(Factories.STRING, Factories.INTEGER).create(),
          _obj.has("v7") ? v7.fromJson(_obj.get("v7")) : HashSetHelpers.create(Factories.arrayList(1, 2, 3)),
          _obj.has("v7a") ? v7a.fromJson(_obj.get("v7a")) : HashSetHelpers.factory(Factories.INTEGER).create(),
          _obj.has("v8") ? v8.fromJson(_obj.get("v8")) : HashMapHelpers.factory(Factories.STRING, Factories.INTEGER).create(),
          _obj.has("v8a") ? v8a.fromJson(_obj.get("v8a")) : HashMapHelpers.create(Factories.arrayList(new Pair<String, Integer>("X", 1), new Pair<String, Integer>("Y", 2)))
        );
      }
    };
  }
}