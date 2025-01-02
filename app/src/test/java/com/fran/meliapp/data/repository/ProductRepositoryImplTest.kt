package com.fran.meliapp.data.repository

import android.util.Log
import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.isNotEmpty
import com.fran.meliapp.data.remote.MeliApi
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ProductRepositoryImplTest {

    private lateinit var repository: ProductRepositoryImpl
    private lateinit var api: MeliApi
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        api = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create()
        repository = ProductRepositoryImpl(api)
    }
    @Test
    fun `Search products response successful, two products found`() = runTest {
        // Given
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody("""
                {
                    "site_id": "MLA",
                    "country_default_time_zone": "GMT-03:00",
                    "query": "a",
                    "paging": {
                        "total": 0,
                        "primary_results": 0,
                        "offset": 0,
                        "limit": 50
                    },
                    "results": [
        {
            "id": "MLA1492694800",
            "title": "Short Mujer Algodon Rustico Dama",
            "condition": "new",
            "thumbnail_id": "853719-MLA78596012839_082024",
            "catalog_product_id": null,
            "listing_type_id": "gold_special",
            "sanitized_title": "",
            "permalink": "https://articulo.mercadolibre.com.ar/MLA-1492694800-short-mujer-algodon-rustico-dama-_JM",
            "buying_mode": "buy_it_now",
            "site_id": "MLA",
            "category_id": "MLA109276",
            "domain_id": "MLA-SHORTS",
            "variation_id": "180274632975",
            "thumbnail": "http://http2.mlstatic.com/D_676140-MLA73080624981_112023-O.jpg",
            "currency_id": "ARS",
            "order_backend": 1,
            "price": 6993,
            "original_price": 7770,
            "sale_price": {
                "price_id": "",
                "amount": 6993,
                "conditions": {
                    "eligible": true,
                    "context_restrictions": [
                        "channel_marketplace"
                    ],
                    "start_time": "2024-12-30T03:00:00Z",
                    "end_time": "2025-01-20T03:00:00Z"
                },
                "currency_id": "ARS",
                "exchange_rate": null,
                "payment_method_prices": [],
                "payment_method_type": "TMP",
                "regular_amount": 7770,
                "type": "promotion",
                "metadata": {
                    "promotion_id": "OFFER-MLA1492694800-10243239585",
                    "promotion_type": "campaign",
                    "campaign_id": "P-MLA14525060"
                }
            },
            "available_quantity": 50,
            "official_store_id": null,
            "use_thumbnail_id": false,
            "accepts_mercadopago": true,
            "variation_filters": [
                "COLOR"
            ],
            "shipping": {
                "store_pick_up": false,
                "free_shipping": false,
                "logistic_type": "fulfillment",
                "mode": "me2",
                "tags": [
                    "fulfillment"
                ],
                "benefits": null,
                "promise": null,
                "shipping_score": -1
            },
            "stop_time": "2043-08-27T04:00:00.000Z",
            "seller": {
                "id": 800595744,
                "nickname": "MYRY"
            },
            "address": {
                "state_id": "AR-B",
                "state_name": "Buenos Aires",
                "city_id": null,
                "city_name": "9 De Abril"
            },
            "attributes": [
                {
                    "id": "SECONDARY_COLOR",
                    "name": "Color secundario",
                    "value_id": null,
                    "value_name": "Gris",
                    "attribute_group_id": "",
                    "attribute_group_name": "",
                    "value_struct": null,
                    "values": [
                        {
                            "id": null,
                            "name": "Gris",
                            "struct": null,
                            "source": 3533618722925375
                        }
                    ],
                    "source": 3533618722925375,
                    "value_type": "string"
                },
                {
                    "id": "BRAND",
                    "name": "Marca",
                    "value_id": null,
                    "value_name": "myry",
                    "attribute_group_id": "MAIN",
                    "attribute_group_name": "Principales",
                    "value_struct": null,
                    "values": [
                        {
                            "id": null,
                            "name": "myry",
                            "struct": null,
                            "source": 6808261514773724
                        }
                    ],
                    "source": 6808261514773724,
                    "value_type": "string"
                },
                {
                    "id": "MODEL",
                    "name": "Modelo",
                    "value_id": null,
                    "value_name": "rustico",
                    "attribute_group_id": "MAIN",
                    "attribute_group_name": "Principales",
                    "value_struct": null,
                    "values": [
                        {
                            "id": null,
                            "name": "rustico",
                            "struct": null,
                            "source": 6808261514773724
                        }
                    ],
                    "source": 6808261514773724,
                    "value_type": "string"
                },
                {
                    "id": "AGE_GROUP",
                    "name": "Edad",
                    "value_id": "6725189",
                    "value_name": "Adultos",
                    "attribute_group_id": "OTHERS",
                    "attribute_group_name": "Otros",
                    "value_struct": null,
                    "values": [
                        {
                            "id": "6725189",
                            "name": "Adultos",
                            "struct": null,
                            "source": 2579503448603610
                        }
                    ],
                    "source": 2579503448603610,
                    "value_type": "list"
                },
                {
                    "id": "FILTRABLE_GENDER",
                    "name": "Género filtrables",
                    "value_id": "18549361",
                    "value_name": "Mujer",
                    "attribute_group_id": "OTHERS",
                    "attribute_group_name": "Otros",
                    "value_struct": null,
                    "values": [
                        {
                            "id": "18549361",
                            "name": "Mujer",
                            "struct": null,
                            "source": 2579503448603610
                        }
                    ],
                    "source": 2579503448603610,
                    "value_type": "list"
                },
                {
                    "id": "GENDER",
                    "name": "Género",
                    "value_id": "339665",
                    "value_name": "Mujer",
                    "attribute_group_id": "OTHERS",
                    "attribute_group_name": "Otros",
                    "value_struct": null,
                    "values": [
                        {
                            "id": "339665",
                            "name": "Mujer",
                            "struct": null,
                            "source": 6808261514773724
                        }
                    ],
                    "source": 6808261514773724,
                    "value_type": "list"
                },
                {
                    "id": "IS_EMERGING_BRAND",
                    "name": "Es marca emergente",
                    "value_id": "242084",
                    "value_name": "No",
                    "attribute_group_id": "OTHERS",
                    "attribute_group_name": "Otros",
                    "value_struct": null,
                    "values": [
                        {
                            "id": "242084",
                            "name": "No",
                            "struct": null,
                            "source": 2579503448603610
                        }
                    ],
                    "source": 2579503448603610,
                    "value_type": "boolean"
                },
                {
                    "id": "IS_HIGHLIGHT_BRAND",
                    "name": "Es marca destacada",
                    "value_id": "242084",
                    "value_name": "No",
                    "attribute_group_id": "OTHERS",
                    "attribute_group_name": "Otros",
                    "value_struct": null,
                    "values": [
                        {
                            "id": "242084",
                            "name": "No",
                            "struct": null,
                            "source": 2579503448603610
                        }
                    ],
                    "source": 2579503448603610,
                    "value_type": "boolean"
                },
                {
                    "id": "IS_TOM_BRAND",
                    "name": "Es marca TOM",
                    "value_id": "242084",
                    "value_name": "No",
                    "attribute_group_id": "OTHERS",
                    "attribute_group_name": "Otros",
                    "value_struct": null,
                    "values": [
                        {
                            "id": "242084",
                            "name": "No",
                            "struct": null,
                            "source": 2579503448603610
                        }
                    ],
                    "source": 2579503448603610,
                    "value_type": "boolean"
                },
                {
                    "id": "ITEM_CONDITION",
                    "name": "Condición del ítem",
                    "value_id": "2230284",
                    "value_name": "Nuevo",
                    "attribute_group_id": "OTHERS",
                    "attribute_group_name": "Otros",
                    "value_struct": null,
                    "values": [
                        {
                            "id": "2230284",
                            "name": "Nuevo",
                            "struct": null,
                            "source": 6808261514773724
                        }
                    ],
                    "source": 6808261514773724,
                    "value_type": "list"
                }
            ],
            "variations_data": {
                "182206116962": {
                    "thumbnail": "http://http2.mlstatic.com/D_614658-MLA80097190075_102024-O.jpg",
                    "ratio": "1.16",
                    "name": "camel",
                    "pictures_qty": 1,
                    "price": 7770,
                    "inventory_id": "OOBF39035",
                    "user_product_id": "MLAU2754573957",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "camel",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "186447524095": {
                    "thumbnail": "http://http2.mlstatic.com/D_841325-MLA72290672751_102023-O.jpg",
                    "ratio": "0.67",
                    "name": "Rosa",
                    "pictures_qty": 1,
                    "price": 7770,
                    "inventory_id": "PKUD56941",
                    "user_product_id": "MLAU2898744509",
                    "attributes": [
                        {
                            "id": "GTIN",
                            "name": "Código universal de producto",
                            "value_name": "string",
                            "value_type": null
                        }
                    ],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Rosa",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "180369012659": {
                    "thumbnail": "http://http2.mlstatic.com/D_676140-MLA73080624981_112023-O.jpg",
                    "ratio": "1.13",
                    "name": "Crema",
                    "pictures_qty": 2,
                    "price": 7770,
                    "inventory_id": "YMYJ91589",
                    "user_product_id": "MLAU209881614",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Crema",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "180369012661": {
                    "thumbnail": "http://http2.mlstatic.com/D_853719-MLA78596012839_082024-O.jpg",
                    "ratio": "1.11",
                    "name": "Lila",
                    "pictures_qty": 2,
                    "price": 7770,
                    "inventory_id": "YOTX94166",
                    "user_product_id": "MLAU209875768",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Lila",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "180369012671": {
                    "thumbnail": "http://http2.mlstatic.com/D_853719-MLA78596012839_082024-O.jpg",
                    "ratio": "1.11",
                    "name": "Lila",
                    "pictures_qty": 2,
                    "price": 7770,
                    "inventory_id": "IIKC93555",
                    "user_product_id": "MLAU209883716",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Lila",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "180368416925": {
                    "thumbnail": "http://http2.mlstatic.com/D_982685-MLA71457674809_092023-O.jpg",
                    "ratio": "1.58",
                    "name": "Gris",
                    "pictures_qty": 5,
                    "price": 7770,
                    "inventory_id": "ZWDF92938",
                    "user_product_id": "MLAU208714351",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Gris",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "180741667465": {
                    "thumbnail": "http://http2.mlstatic.com/D_853719-MLA78596012839_082024-O.jpg",
                    "ratio": "1.11",
                    "name": "Lila",
                    "pictures_qty": 2,
                    "price": 7770,
                    "inventory_id": "HTQK95683",
                    "user_product_id": "MLAU208728091",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Lila",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "180275239619": {
                    "thumbnail": "http://http2.mlstatic.com/D_853719-MLA78596012839_082024-O.jpg",
                    "ratio": "1.11",
                    "name": "Lila",
                    "pictures_qty": 2,
                    "price": 7770,
                    "inventory_id": "JZFZ93529",
                    "user_product_id": "MLAU208722187",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Lila",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "180368416927": {
                    "thumbnail": "http://http2.mlstatic.com/D_982685-MLA71457674809_092023-O.jpg",
                    "ratio": "1.58",
                    "name": "Gris",
                    "pictures_qty": 5,
                    "price": 7770,
                    "inventory_id": "JFWD91298",
                    "user_product_id": "MLAU208726195",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Gris",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "180369012639": {
                    "thumbnail": "http://http2.mlstatic.com/D_676140-MLA73080624981_112023-O.jpg",
                    "ratio": "1.13",
                    "name": "Crema",
                    "pictures_qty": 2,
                    "price": 7770,
                    "inventory_id": "QVOK90999",
                    "user_product_id": "MLAU209879714",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Crema",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "180369012651": {
                    "thumbnail": "http://http2.mlstatic.com/D_853719-MLA78596012839_082024-O.jpg",
                    "ratio": "1.11",
                    "name": "Lila",
                    "pictures_qty": 2,
                    "price": 7770,
                    "inventory_id": "DJTT93000",
                    "user_product_id": "MLAU209871898",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Lila",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "186447524099": {
                    "thumbnail": "http://http2.mlstatic.com/D_841325-MLA72290672751_102023-O.jpg",
                    "ratio": "0.67",
                    "name": "Rosa",
                    "pictures_qty": 1,
                    "price": 7770,
                    "inventory_id": "VNBF56099",
                    "user_product_id": "MLAU2898744511",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Rosa",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "186447524093": {
                    "thumbnail": "http://http2.mlstatic.com/D_841325-MLA72290672751_102023-O.jpg",
                    "ratio": "0.67",
                    "name": "Rosa",
                    "pictures_qty": 1,
                    "price": 7770,
                    "inventory_id": "FKOZ56533",
                    "user_product_id": "MLAU2903827466",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Rosa",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "180369012669": {
                    "thumbnail": "http://http2.mlstatic.com/D_676140-MLA73080624981_112023-O.jpg",
                    "ratio": "1.13",
                    "name": "Crema",
                    "pictures_qty": 2,
                    "price": 7770,
                    "inventory_id": "IUTT91813",
                    "user_product_id": "MLAU209869890",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Crema",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "185635034791": {
                    "thumbnail": "http://http2.mlstatic.com/D_884162-MLA72490442825_102023-O.jpg",
                    "ratio": "1.13",
                    "name": "Fucsia",
                    "pictures_qty": 1,
                    "price": 7770,
                    "inventory_id": "IOBV25267",
                    "user_product_id": "MLAU2463491285",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Fucsia",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "185740075377": {
                    "thumbnail": "http://http2.mlstatic.com/D_884162-MLA72490442825_102023-O.jpg",
                    "ratio": "1.13",
                    "name": "Fucsia",
                    "pictures_qty": 1,
                    "price": 7770,
                    "inventory_id": "VHDQ28998",
                    "user_product_id": "MLAU2566552086",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Fucsia",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "185740075381": {
                    "thumbnail": "http://http2.mlstatic.com/D_884162-MLA72490442825_102023-O.jpg",
                    "ratio": "1.13",
                    "name": "Fucsia",
                    "pictures_qty": 1,
                    "price": 7770,
                    "inventory_id": "SBLI28539",
                    "user_product_id": "MLAU2561745493",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Fucsia",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "186200573723": {
                    "thumbnail": "http://http2.mlstatic.com/D_884162-MLA72490442825_102023-O.jpg",
                    "ratio": "1.13",
                    "name": "Fucsia",
                    "pictures_qty": 1,
                    "price": 7770,
                    "inventory_id": "QJRZ47625",
                    "user_product_id": "MLAU2866950396",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Fucsia",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "180274658215": {
                    "thumbnail": "http://http2.mlstatic.com/D_841325-MLA72290672751_102023-O.jpg",
                    "ratio": "0.67",
                    "name": "Rosa",
                    "pictures_qty": 1,
                    "price": 7770,
                    "inventory_id": "UBUK90307",
                    "user_product_id": "MLAU208720207",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Rosa",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "180274632975": {
                    "thumbnail": "http://http2.mlstatic.com/D_676140-MLA73080624981_112023-O.jpg",
                    "ratio": "1.13",
                    "name": "Crema",
                    "pictures_qty": 2,
                    "price": 7770,
                    "inventory_id": "DJFQ92226",
                    "user_product_id": "MLAU209865832",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Crema",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "180369012641": {
                    "thumbnail": "http://http2.mlstatic.com/D_853719-MLA78596012839_082024-O.jpg",
                    "ratio": "1.11",
                    "name": "Lila",
                    "pictures_qty": 2,
                    "price": 7770,
                    "inventory_id": "AVSJ94023",
                    "user_product_id": "MLAU209885592",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Lila",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "185590626681": {
                    "thumbnail": "http://http2.mlstatic.com/D_614658-MLA80097190075_102024-O.jpg",
                    "ratio": "1.16",
                    "name": "camel",
                    "pictures_qty": 1,
                    "price": 7770,
                    "inventory_id": "SWCP23972",
                    "user_product_id": "MLAU2441064201",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "camel",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "186447524091": {
                    "thumbnail": "http://http2.mlstatic.com/D_841325-MLA72290672751_102023-O.jpg",
                    "ratio": "0.67",
                    "name": "Rosa",
                    "pictures_qty": 1,
                    "price": 7770,
                    "inventory_id": "VPKP56496",
                    "user_product_id": "MLAU2903828660",
                    "attributes": [],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Rosa",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                }
            },
            "installments": {
                "quantity": 6,
                "amount": 1298.48,
                "rate": 11.41,
                "currency_id": "ARS",
                "metadata": {
                    "meliplus_installments": false,
                    "additional_bank_interest": false
                }
            },
            "winner_item_id": null,
            "catalog_listing": false,
            "discounts": null,
            "promotion_decorations": null,
            "promotions": null,
            "inventory_id": null
        },
        {
            "id": "MLA899101099",
            "title": "Short Deportivo Cargo Hombre Bolsillos Laterales - Shcargo",
            "condition": "new",
            "thumbnail_id": "701872-MLA51620820110_092022",
            "catalog_product_id": null,
            "listing_type_id": "gold_special",
            "sanitized_title": "",
            "permalink": "https://articulo.mercadolibre.com.ar/MLA-899101099-short-deportivo-cargo-hombre-bolsillos-laterales-shcargo-_JM",
            "buying_mode": "buy_it_now",
            "site_id": "MLA",
            "category_id": "MLA417444",
            "domain_id": "MLA-SPORT_SHORTS",
            "variation_id": "69398419199",
            "thumbnail": "http://http2.mlstatic.com/D_701872-MLA51620820110_092022-O.jpg",
            "currency_id": "ARS",
            "order_backend": 2,
            "price": 34000,
            "original_price": null,
            "sale_price": {
                "price_id": "",
                "amount": 34000,
                "conditions": {
                    "eligible": true,
                    "context_restrictions": [],
                    "start_time": null,
                    "end_time": null
                },
                "currency_id": "ARS",
                "exchange_rate": null,
                "payment_method_prices": [],
                "payment_method_type": "TMP",
                "regular_amount": null,
                "type": "standard",
                "metadata": {}
            },
            "available_quantity": 50,
            "official_store_id": null,
            "use_thumbnail_id": false,
            "accepts_mercadopago": true,
            "variation_filters": [
                "COLOR"
            ],
            "shipping": {
                "store_pick_up": false,
                "free_shipping": true,
                "logistic_type": "fulfillment",
                "mode": "me2",
                "tags": [
                    "fulfillment",
                    "MLA-chg-threshold-Feb-23",
                    "self_service_in",
                    "mandatory_free_shipping"
                ],
                "benefits": null,
                "promise": null,
                "shipping_score": -1
            },
            "stop_time": "2040-11-22T04:00:00.000Z",
            "seller": {
                "id": 445140793,
                "nickname": "ZAPPASION"
            },
            "address": {
                "state_id": "AR-C",
                "state_name": "Capital Federal",
                "city_id": "TUxBQlZJTDc4MDda",
                "city_name": "Villa del Parque"
            },
            "attributes": [
                {
                    "id": "AGE_GROUP",
                    "name": "Edad",
                    "value_id": "6725189",
                    "value_name": "Adultos",
                    "attribute_group_id": "OTHERS",
                    "attribute_group_name": "Otros",
                    "value_struct": null,
                    "values": [
                        {
                            "id": "6725189",
                            "name": "Adultos",
                            "struct": null,
                            "source": 2579503448603610
                        }
                    ],
                    "source": 2579503448603610,
                    "value_type": "list"
                },
                {
                    "id": "BRAND",
                    "name": "Marca",
                    "value_id": "13059545",
                    "value_name": "Urban Luxury",
                    "attribute_group_id": "OTHERS",
                    "attribute_group_name": "Otros",
                    "value_struct": null,
                    "values": [
                        {
                            "id": "13059545",
                            "name": "Urban Luxury",
                            "struct": null,
                            "source": 1261
                        }
                    ],
                    "source": 1261,
                    "value_type": "string"
                },
                {
                    "id": "FILTRABLE_GENDER",
                    "name": "Género filtrables",
                    "value_id": "18549360",
                    "value_name": "Hombre",
                    "attribute_group_id": "OTHERS",
                    "attribute_group_name": "Otros",
                    "value_struct": null,
                    "values": [
                        {
                            "id": "18549360",
                            "name": "Hombre",
                            "struct": null,
                            "source": 2579503448603610
                        }
                    ],
                    "source": 2579503448603610,
                    "value_type": "list"
                },
                {
                    "id": "GENDER",
                    "name": "Género",
                    "value_id": "339666",
                    "value_name": "Hombre",
                    "attribute_group_id": "OTHERS",
                    "attribute_group_name": "Otros",
                    "value_struct": null,
                    "values": [
                        {
                            "id": "339666",
                            "name": "Hombre",
                            "struct": null,
                            "source": 3376461333454861
                        }
                    ],
                    "source": 3376461333454861,
                    "value_type": "list"
                },
                {
                    "id": "IS_EMERGING_BRAND",
                    "name": "Es marca emergente",
                    "value_id": "242084",
                    "value_name": "No",
                    "attribute_group_id": "OTHERS",
                    "attribute_group_name": "Otros",
                    "value_struct": null,
                    "values": [
                        {
                            "id": "242084",
                            "name": "No",
                            "struct": null,
                            "source": 2579503448603610
                        }
                    ],
                    "source": 2579503448603610,
                    "value_type": "boolean"
                },
                {
                    "id": "IS_HIGHLIGHT_BRAND",
                    "name": "Es marca destacada",
                    "value_id": "242084",
                    "value_name": "No",
                    "attribute_group_id": "OTHERS",
                    "attribute_group_name": "Otros",
                    "value_struct": null,
                    "values": [
                        {
                            "id": "242084",
                            "name": "No",
                            "struct": null,
                            "source": 2579503448603610
                        }
                    ],
                    "source": 2579503448603610,
                    "value_type": "boolean"
                },
                {
                    "id": "IS_TOM_BRAND",
                    "name": "Es marca TOM",
                    "value_id": "242084",
                    "value_name": "No",
                    "attribute_group_id": "OTHERS",
                    "attribute_group_name": "Otros",
                    "value_struct": null,
                    "values": [
                        {
                            "id": "242084",
                            "name": "No",
                            "struct": null,
                            "source": 2579503448603610
                        }
                    ],
                    "source": 2579503448603610,
                    "value_type": "boolean"
                },
                {
                    "id": "ITEM_CONDITION",
                    "name": "Condición del ítem",
                    "value_id": "2230284",
                    "value_name": "Nuevo",
                    "attribute_group_id": "OTHERS",
                    "attribute_group_name": "Otros",
                    "value_struct": null,
                    "values": [
                        {
                            "id": "2230284",
                            "name": "Nuevo",
                            "struct": null,
                            "source": 1
                        }
                    ],
                    "source": 1,
                    "value_type": "list"
                },
                {
                    "id": "MODEL",
                    "name": "Modelo",
                    "value_id": null,
                    "value_name": "DEPORTIVO",
                    "attribute_group_id": "OTHERS",
                    "attribute_group_name": "Otros",
                    "value_struct": null,
                    "values": [
                        {
                            "id": null,
                            "name": "DEPORTIVO",
                            "struct": null,
                            "source": 3376461333454861
                        }
                    ],
                    "source": 3376461333454861,
                    "value_type": "string"
                }
            ],
            "variations_data": {
                "69398419207": {
                    "thumbnail": "http://http2.mlstatic.com/D_701872-MLA51620820110_092022-O.jpg",
                    "ratio": "1.00",
                    "name": "Negro",
                    "pictures_qty": 7,
                    "price": 34000,
                    "inventory_id": "VVUY68330",
                    "user_product_id": "MLAU139912878",
                    "attributes": [
                        {
                            "id": "GTIN",
                            "name": "Código universal de producto",
                            "value_name": "string",
                            "value_type": null
                        }
                    ],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Negro",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "69398419199": {
                    "thumbnail": "http://http2.mlstatic.com/D_701872-MLA51620820110_092022-O.jpg",
                    "ratio": "1.00",
                    "name": "Negro",
                    "pictures_qty": 7,
                    "price": 34000,
                    "inventory_id": "OFKE67478",
                    "user_product_id": "MLAU139922316",
                    "attributes": [
                        {
                            "id": "GTIN",
                            "name": "Código universal de producto",
                            "value_name": "string",
                            "value_type": null
                        }
                    ],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Negro",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "69398419216": {
                    "thumbnail": "http://http2.mlstatic.com/D_701872-MLA51620820110_092022-O.jpg",
                    "ratio": "1.00",
                    "name": "Negro",
                    "pictures_qty": 7,
                    "price": 34000,
                    "inventory_id": "JPBQ66809",
                    "user_product_id": "MLAU139932248",
                    "attributes": [
                        {
                            "id": "GTIN",
                            "name": "Código universal de producto",
                            "value_name": "string",
                            "value_type": null
                        }
                    ],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Negro",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "69398419230": {
                    "thumbnail": "http://http2.mlstatic.com/D_701872-MLA51620820110_092022-O.jpg",
                    "ratio": "1.00",
                    "name": "Negro",
                    "pictures_qty": 7,
                    "price": 34000,
                    "inventory_id": "QEOZ67840",
                    "user_product_id": "MLAU139912882",
                    "attributes": [
                        {
                            "id": "GTIN",
                            "name": "Código universal de producto",
                            "value_name": "string",
                            "value_type": null
                        }
                    ],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Negro",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "69398419224": {
                    "thumbnail": "http://http2.mlstatic.com/D_701872-MLA51620820110_092022-O.jpg",
                    "ratio": "1.00",
                    "name": "Negro",
                    "pictures_qty": 7,
                    "price": 34000,
                    "inventory_id": "VMBL68400",
                    "user_product_id": "MLAU139908928",
                    "attributes": [
                        {
                            "id": "GTIN",
                            "name": "Código universal de producto",
                            "value_name": "string",
                            "value_type": null
                        }
                    ],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Negro",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                },
                "180980114949": {
                    "thumbnail": "http://http2.mlstatic.com/D_701872-MLA51620820110_092022-O.jpg",
                    "ratio": "1.00",
                    "name": "Negro",
                    "pictures_qty": 7,
                    "price": 34000,
                    "inventory_id": "DXCH01109",
                    "user_product_id": "MLAU139117901",
                    "attributes": [
                        {
                            "id": "GTIN",
                            "name": "Código universal de producto",
                            "value_name": "string",
                            "value_type": null
                        },
                        {
                            "id": "",
                            "name": "STOCK",
                            "value_name": "string",
                            "value_type": "0"
                        }
                    ],
                    "attribute_combinations": [
                        {
                            "id": "COLOR",
                            "name": "Color",
                            "value_id": null,
                            "value_name": "Negro",
                            "value_struct": null,
                            "values": null
                        }
                    ]
                }
            },
            "installments": {
                "quantity": 6,
                "amount": 7642.07,
                "rate": 34.86,
                "currency_id": "ARS",
                "metadata": {
                    "meliplus_installments": false,
                    "additional_bank_interest": false
                }
            },
            "winner_item_id": null,
            "catalog_listing": false,
            "discounts": null,
            "promotion_decorations": null,
            "promotions": null,
            "inventory_id": null
        }
                    ],
                    "sort": {
                        "id": "relevance",
                        "name": "Más relevantes"
                    },
                    "available_sorts": [
                        {
                            "id": "price_asc",
                            "name": "Menor precio"
                        },
                        {
                            "id": "price_desc",
                            "name": "Mayor precio"
                        }
                    ],
                    "filters": [],
                    "available_filters": [],
                    "pdp_tracking": {
                        "group": false,
                        "product_info": []
                    },
                    "user_context": null
                }
            """.trimIndent())
        )

        // When
        val products = repository.searchProducts("test query")

        // Then
        assertk.assertThat(products.size).isEqualTo(2)
    }

    @Test
    fun `Search products response successful, no products found`() = runTest {
        // Given
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody("""
                {
                    "site_id": "MLA",
                    "country_default_time_zone": "GMT-03:00",
                    "query": "a",
                    "paging": {
                        "total": 0,
                        "primary_results": 0,
                        "offset": 0,
                        "limit": 50
                    },
                    "results": [],
                    "sort": {
                        "id": "relevance",
                        "name": "Más relevantes"
                    },
                    "available_sorts": [
                        {
                            "id": "price_asc",
                            "name": "Menor precio"
                        },
                        {
                            "id": "price_desc",
                            "name": "Mayor precio"
                        }
                    ],
                    "filters": [],
                    "available_filters": [],
                    "pdp_tracking": {
                        "group": false,
                        "product_info": []
                    },
                    "user_context": null
                }
            """.trimIndent())
        )

        // When
        val products = repository.searchProducts("test query")

        // Then
        assertk.assertThat(products).isEmpty()
    }

    @Test
    fun `Search products response not successful`() = runTest {
        mockkStatic(Log::class)
        every { Log.e(any(), any()) } returns 0
        // Given
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(500)
        )

        // When
        val exception = assertThrows<Exception> {
            repository.searchProducts("test query")
        }

        // Then
        assertThat(exception).isInstanceOf(RuntimeException::class.java)
    }

    @Test
    fun `Get product description response successful`() = runTest {
        // Given
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody("""{
    "text": "",
    "plain_text": "Short mujer\nTela algodon rustico\nTalles del 2 al 7\n\nTalle ----------- Cadera -- Largo --- Cintura\ntalle 2 -- (38)-- 90 cm --33 cm -- 58-84 cm\ntalle 3 --(40) --94 cm --34 cm -- 60-86 cm\ntalle 4 --(42) --98 cm --36 cm -- 62-88 cm\ntalle 5 --(44) --102cm --38 cm -- 64-90 cm\ntalle 6 --(46) --106cm --40 cm-- 66-95 cm\ntalle 7 --(48)-- 110cm --41 cm-- 69- 110cm\nColores disponibles\nGris\nRosa\nNegro\nLila \nfucsia \nverde lima\n\n-----------TENEMOS OTRAS PUBLICACIONES \n------------Pack X 3 \n------------Pack x 4 \n\n\n-Cualquier consulta no dudes en escribirnos\n-Nuestro horario de atención es de lunes a sábado de 9 a 18 hs\n-Despachos de lunes a viernes\n\nRealizamos factura A y B\n\nNo te pierdas todos los otros modelos que tenemos!\n\nREALIZAMOS ENVÍOS A TODO EL PAÍS.\n\nENVÍO NORMAL A DOMICILIO: Enviamos en el día al correo.\n\nVenta directa de fabrica\n\nSomos un emprendimiento familiar, nos dedicamos a la fabricacion de indumentaria y calzado femenino\n\nSumate a nuestras revendedoras\nEnvios a todo el pais",
    "last_updated": "2024-01-13T22:38:32.470Z",
    "date_created": "2023-09-01T20:36:05.811Z",
    "snapshot": {
        "url": "http://descriptions.mlstatic.com/D-MLA1492694800.jpg?hash=8520c3b8559cb08aa7e782b8f5334ffe_0x0",
        "width": 0,
        "height": 0,
        "status": ""
    }
}
            """.trimIndent())
        )

        // When
        val productDescription = repository.getProductDescription("test id")

        // Then
        assertThat(productDescription.description).isNotEmpty()
    }

    @Test
    fun `Get product description response not successful`() = runTest {
        mockkStatic(Log::class)
        every { Log.e(any(), any()) } returns 0
        // Given
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(500)
        )

        // When
        val exception = assertThrows<Exception> {
            repository.searchProducts("test query")
        }

        // Then
        assertThat(exception).isInstanceOf(RuntimeException::class.java)
    }
}
