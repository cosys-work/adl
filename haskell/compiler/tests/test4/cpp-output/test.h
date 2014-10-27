#ifndef TEST_H
#define TEST_H
#include "Date.h"
#include <adl/adl.h>
#include <string>

namespace ADL {
namespace test {

// Date generated as DateO due to custom definition


struct DateO
{
    DateO() : value("1900-01-01") {}
    explicit DateO(const std::string & v) : value(v) {}
    
    std::string value;
};

inline
bool operator<( const DateO &a, const DateO &b ) { return a.value < b.value; }
inline
bool operator==( const DateO &a, const DateO &b ) { return a.value == b.value; }

struct S
{
    S();
    
    S(
        const Date & v1
        );
    
    Date v1;
};

bool operator<( const S &a, const S &b );
bool operator==( const S &a, const S &b );

}}; // ADL::test

namespace ADL {

template <>
struct Serialisable<ADL::test::DateO>
{
    struct S : public Serialiser<ADL::test::DateO>
    {
        S( typename Serialiser<std::string>::Ptr s_ ) : s(s_) {}
        
        void toJson( JsonWriter &json, const ADL::test::DateO & v ) const
        {
            s->toJson( json, v.value );
        }
        
        void fromJson( ADL::test::DateO &v, JsonReader &json ) const
        {
            s->fromJson( v.value, json );
        }
        
        typename Serialiser<std::string>::Ptr s;
    };
    
    static typename Serialiser<ADL::test::DateO>::Ptr serialiser(const SerialiserFlags &sf)
    {
        return typename Serialiser<ADL::test::DateO>::Ptr(new S(Serialisable<std::string>::serialiser(sf)));
    }
};

template <>
struct Serialisable<ADL::test::S>
{
    static Serialiser<ADL::test::S>::Ptr serialiser(const SerialiserFlags &);
};

}; // ADL
#endif // TEST_H