# ArduinoDoc

A Javadoc style Arduino documentation generator.

## Usage

Before the start of a function insert a comment in the following way:

```cpp
/*
 * Description of the function
 */
```

you can also insert a description for all the paramaters of the function with the tag ```@param``` :

```cpp
/*
 * Description of the function
 *
 * @param <param name> <param description>
 * ...
 * @param <param name> <param description>
 */
```

and a description for the returned value with the tag ```@return``` :

```cpp
/*
 * Description of the function
 *
 * @return <description>
 */
```

Multiple tags are allowed:

```cpp
/*
 * Description of the function
 *
 * @param <param name> <param description>
 * ...
 * @param <param name> <param description>
 * @return <description>
 */
```

<b>Attention: function without at least a description will be ignored.</b>

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
