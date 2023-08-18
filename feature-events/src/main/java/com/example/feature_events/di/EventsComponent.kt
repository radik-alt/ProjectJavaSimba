package com.example.feature_events.di

import com.example.feature_events.presentation.filter_news.view.FilterFragment
import com.example.feature_events.presentation.news.view.NewsFragment
import dagger.Subcomponent

@Subcomponent(
    modules = [
        DataModule::class,
        DomainModule::class,
        ViewModelModule::class
    ]
)
interface EventsComponent {

    fun inject(newsFragment: NewsFragment)
    fun inject(filterFragment: FilterFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): EventsComponent
    }
}